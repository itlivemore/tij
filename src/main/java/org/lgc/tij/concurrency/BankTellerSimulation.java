package org.lgc.tij.concurrency;

import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 银行出纳员仿真
 * 适用情况：对象随机地出现，并且要求数量有限的服务器提供随机数量的服务时间。通过构建仿真可以确定理想的服务器数量。
 * 在本例中，每个银行顾客要求一定数量的服务时间，这是出纳员必须花费在顾客身上，以服务顾客需求的时间单位数量。
 * 服务时间的数量对每个顾客来说都是不同的，并且是随机确定的。
 * 另外，你不知道在每个时间间隔内有多少顾客会到达，因此这也是随机确定的。
 * 总体思路：Customer类表示顾客，每个顾客都需要一个服务时间。
 * CustomerLine类继承了ArrayBlockingQueue，表示顾客排队队列。
 * CustomerGenerator类随机生成Customer并添加到CustomerLine队列里。
 * Teller类表示银行出纳员，出纳员会从CustomerLine队列里取出Customer，并按Customer中的服务时间来提供服务。
 * TellerManage是管理类，保存了正在服务顾客的出纳员信息(workingTellers)及做其它事情的出纳员信息(tellersDoingOtherTings),
 * 根据CustomerLine队列里的人数及workingTellers人数比例来确定是否要向workingTellers添加或者删除出纳员。
 * Created by laigc on 2017/3/26.
 */

// 顾客类，只读，不需要同步
class Customer {
    private final int serviceTime; // 顾客需要的服务时间

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "serviceTime:[" + serviceTime + "]";
    }
}


/**
 * 顾客排队队列
 * ArrayBlockingQueue是一个有限长度队列。
 * 试图向已满队列中添加元素会被阻塞，试图从空队列中取出元素会被阻塞。
 */
class CustomerLine extends ArrayBlockingQueue<Customer> {
    public CustomerLine(int capacity) {
        super(capacity);
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return "[Empty]";
        }
        // 拼接队列里的顾客
        StringBuilder sb = new StringBuilder();
        for (Customer customer : this) {
            sb.append(customer);
        }
        return sb.toString();
    }
}

/**
 * 顾客生成类
 * 间隔一段随机时间，然后向顾客队列中添加一个顾客
 */
class CustomerGenerator implements Runnable {
    private CustomerLine customers; // 顾客队列
    private static Random random = new Random(47);

    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 间隔随机时间
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                // 向队列中添加一个顾客
                customers.put(new Customer(random.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.out.println("CustomerGenerator interrupted");
        }
        System.out.println("CustomerGenerator terminating");
    }
}

/**
 * 银行出纳员类
 * 可以安排出纳员做两项工作，为顾客服务及做一些其他事
 */
class Teller implements Runnable, Comparable<Teller> {
    private static int counter = 0;
    private final int id = counter++; //出纳员id

    private int customersServed = 0; // 当前出纳员服务了多少个顾客
    private CustomerLine customers; // 当前出纳员服务的顾客队列
    private boolean servingCustomerLine = true; // 当前是否有顾客队列

    public Teller(CustomerLine customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 从队列中取出顾客并进行服务一段时间
                Customer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());

                synchronized (this) {
                    customersServed++;// 服务顾客的人数+1
                    while (!servingCustomerLine) {// 没有顾客队列
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " terminating");
    }

    // 表示出纳员被分配做其它的事
    public synchronized void doSomethingElse() {
        customersServed = 0;
        servingCustomerLine = false;
    }

    // 表示出纳员的工作是服务顾客
    public synchronized void serveCustomerLine() {
        assert !servingCustomerLine : "already serving: " + this;
        servingCustomerLine = true;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Teller " + id + " ";
    }

    public String shortString() {
        return "T" + id;
    }

    /*按已经服务顾客的数量进行优先级排序，用于优先级队列选出服务顾客最少的出纳员*/
    @Override
    public synchronized int compareTo(Teller o) {
        return customersServed < o.customersServed ? -1 : (customersServed == o.customersServed ? 0 : 1);
    }
}

/**
 * 管理Teller的类，根据实际情况调用出纳员工作
 */
class TellerManager implements Runnable {
    private ExecutorService exec;
    private CustomerLine customers;
    /* 队列按Teller中的Comparable定义优先级，每次取出服务顾客数量最少的出纳员来做其它工作 */
    private PriorityQueue<Teller> workingTellers = new PriorityQueue<>();
    /*正在做其它事情的出纳员队列*/
    private Queue<Teller> tellersDoingOtherTings = new LinkedList<>();
    private int adjustmentPeriod;// 调整周期，隔多长时间调整出纳员人数
    private static Random random = new Random(47);

    public TellerManager(ExecutorService exec, CustomerLine customers, int adjustmentPeriod) {
        this.exec = exec;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        // 先创建一个出纳员进行服务
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }

    /*通过顾客人数与出纳员服务人数来调整出纳员工作*/
    public void adjustTellerNumber() {
        // 如果顾客队列过长，增加出纳员来服务顾客
        if (customers.size() / workingTellers.size() > 2) {
            // 如果有出纳员在做其它工作，直接调过来
            if (tellersDoingOtherTings.size() > 0) {
                Teller teller = tellersDoingOtherTings.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return;
            }
            // 若没有多余的出纳员，则增加出纳员
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
            return;
        }
        // 顾客人数少，安排一些出纳员做其它工作
        if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2) {
            reassignOneTeller();
        }

        // 如果没有顾客在排队，就只保留一个出纳员服务顾客
        if (customers.size() == 0) {
            while (workingTellers.size() > 1) {
                reassignOneTeller();
            }
        }
    }

    // 安排服务顾客的出纳员做其它工作
    private void reassignOneTeller() {
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherTings.offer(teller);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                // 调整出纳员工作
                adjustTellerNumber();
                // 打印当前customers和workingTellers的情况
                System.out.print(customers + " { ");
                for (Teller teller : workingTellers) {
                    System.out.print(teller.shortString() + " ");
                }
                System.out.println("}");
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " terminating");
    }

    @Override
    public String toString() {
        return "TellerManager ";
    }
}

public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50; // 顾客队列长度
    static final int ADJUSTMENT_PERIOD = 1000; //调整周期

    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);// 创建顾客队列
        threadPool.execute(new CustomerGenerator(customers)); // 随机生成顾客
        threadPool.execute(new TellerManager(threadPool, customers, ADJUSTMENT_PERIOD));
        System.out.println("Press 'Enter' to quit");
        System.in.read();
        threadPool.shutdownNow();
    }
}
