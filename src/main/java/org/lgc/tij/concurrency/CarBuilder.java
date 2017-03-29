package org.lgc.tij.concurrency;

/**
 * 分发工作，洗车装配仿真
 * 模拟一条机器人组装汽车生产线，汽车生产过程首先是创建底盘，然后是安装发动机，动力传动系统，车轮，然后一辆车就生产完成了。
 * Created by lgc on 17-3-27.
 */

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 汽车类
 */
class Car {
    private final int id; // 汽车id
    /*开始时汽车的各个部分都还没有装配*/
    private boolean engine = false, driveTrain = false, wheels = false;

    public Car() {
        id = -1;
    }

    public Car(int id) {
        this.id = id;
    }

    public synchronized int getId() {
        return id;
    }

    /*下面是装配汽车各部分的方法，将相应字段值设置为true表示装配完成*/
    public synchronized void addEngine() {
        engine = true;
    }

    public synchronized void addDriveTrain() {
        driveTrain = true;
    }

    public synchronized void addWheels() {
        wheels = true;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", engine=" + engine +
                ", driveTrain=" + driveTrain +
                ", wheels=" + wheels +
                '}';
    }

    // 简短的打印
    public String shortString() {
        return "car " + id;
    }
}

/**
 * 汽车队列
 */
class CarQueue extends LinkedBlockingDeque<Car> {
}

/**
 * 建造底盘的类
 */
class ChassisBuilder implements Runnable {
    private CarQueue carQueue; // 已经建造好底盘的汽车队列
    private int counter = 0;

    public ChassisBuilder(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(400);
                // 生产汽车底盘
                Car car = new Car(counter++);
                System.out.println("ChassisBuilder created " + car);
                // 将已经生产好底盘的汽车加到入队列中
                carQueue.put(car);
            }
        } catch (InterruptedException e) {
            System.out.println("ChassisBuilder interrupted");
        }
        System.out.println("ChassisBuilder off");
    }
}

/**
 * 组装类，通过调用机器人装配已经装好底盘的汽车的其它部分
 */
class Assembler implements Runnable {
    private CarQueue chassisQueue, finishingQueue; // 已经装好底盘的汽车队列和已经完成组装的汽车队列

    private Car car; // 正在组装的汽车
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;

    public Assembler(CarQueue chassisQueue, CarQueue finishingQueue, RobotPool robotPool) {
        this.chassisQueue = chassisQueue;
        this.finishingQueue = finishingQueue;
        this.robotPool = robotPool;
    }

    public Car car() {
        return car;
    }

    public CyclicBarrier barrier() {
        return barrier;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 取出已经装好底盘的汽车，如果队列为空，会阻塞
                car = chassisQueue.take();
                // 雇佣机器人去组装汽车
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                robotPool.hire(WheelRobot.class, this);
                // barrier要等所有barrier都完成,否则会阻塞，如果前面的三步都还没有完成，则会在这里阻塞，这样可以保证不会有两辆车同时组装
                barrier().await();
                finishingQueue.put(car); // 加入已经完成的车队里
            }
        } catch (InterruptedException e) {
            System.out.println("Assembler interrupted");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Assembler off");
    }
}

/**
 * 将已经生产完成的汽车打印输出
 */
class Reporter implements Runnable {
    private CarQueue carQueue;

    public Reporter(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(carQueue.take() + "生产完成");
            }
        } catch (InterruptedException e) {
            System.out.println("Reporter interrupted");
        }
        System.out.println("Reporter off");
    }
}

/**
 * 负责组装工作的机器人类，是一个抽象类。各种工作的机器人有具体的实现
 */
abstract class Robot implements Runnable {
    private RobotPool pool;

    public Robot(RobotPool pool) {
        this.pool = pool;
    }

    protected Assembler assembler; // 该机器人负责的组装线

    // 连接到组装线
    public Robot assignAssembler(Assembler assembler) {
        this.assembler = assembler;
        return this;
    }

    private boolean engage = false; // 是否正在工作

    /*让机器人工作*/
    public synchronized void engage() {
        engage = true;
        notifyAll();
    }

    /*抽象方法，因为不同的机器人做的工作不一样，要由子类来具体实现*/
    abstract protected void performService();

    @Override
    public void run() {
        try {
            // 开始的时候没有人雇佣，就阻塞等待
            powerDown();
            while (!Thread.interrupted()) {
                performService(); // 工作
                assembler.barrier().await(); // 表示该机器人工作已经完成
                powerDown(); // 工作完成,修改机器人状态
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    // 将机器人修改为非工作状态
    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        assembler = null;// 断开与组装线的连接
        pool.release(this); // 释放为空闲的可用机器人
        // 不工作时等待
        while (!engage) {
            wait();
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}

/**
 * 装配发动机的机器人
 */
class EngineRobot extends Robot {

    public EngineRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing " + assembler.car().shortString() + " engine");
        assembler.car().addEngine();
    }
}

/**
 * 装配传动系统的机器人
 */
class DriveTrainRobot extends Robot {
    public DriveTrainRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing " + assembler.car().shortString() + " DriveTrain");
        assembler.car().addDriveTrain();
    }
}

/**
 * 装配轮子的机器人
 */
class WheelRobot extends Robot {
    public WheelRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing " + assembler.car().shortString() + " Wheels");
        assembler.car().addWheels();
    }
}

/**
 * 机器人管理类
 */
class RobotPool {
    private Set<Robot> pool = new HashSet<>(); // 空闲机器人，防止机器人重复，用Set.

    public synchronized void add(Robot robot) {
        pool.add(robot);
        notifyAll();
    }

    public synchronized void hire(Class<? extends Robot> robotType, Assembler assembler) throws InterruptedException {
        // 从空闲机器人中找出合适工作的机器人
        for (Robot robot : pool) {
            if (robot.getClass().equals(robotType)) {
                pool.remove(robot);
                robot.assignAssembler(assembler);// 连上组装线
                robot.engage(); // 让机器人工作
                return;
            }
        }
        wait(); // 没有可用的机器人
        hire(robotType, assembler); // 递归查找
    }

    // 将机器人放回空闲Set里
    public synchronized void release(Robot robot) {
        add(robot);
    }
}

public class CarBuilder {
    public static void main(String[] args) throws InterruptedException {
        CarQueue chassisQueue = new CarQueue(), finishingQueue = new CarQueue();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        RobotPool robotPool = new RobotPool();
        // 启动机器人与生产线
        threadPool.execute(new EngineRobot(robotPool));
        threadPool.execute(new DriveTrainRobot(robotPool));
        threadPool.execute(new WheelRobot(robotPool));
        threadPool.execute(new Assembler(chassisQueue, finishingQueue, robotPool));
        threadPool.execute(new Reporter(finishingQueue));
        threadPool.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        threadPool.shutdownNow();
    }
}
