package org.lgc.tij.concurrency.restaurant2;

import org.lgc.tij.enumerated.menu.Course;
import org.lgc.tij.enumerated.menu.Food;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 饭店仿真
 * Created by laigc on 2017/3/26.
 */

/**
 * 订单类
 */
class Order {
    private static int counter = 0;
    private final int id = counter++;// 订单id
    private final Customer customer; // 订单对应的顾客
    private final WaitPerson waitPerson; // 订单对应的服务员
    private final Food food; // 订单对应的食物

    public Order(Customer customer, WaitPerson waitPerson, Food food) {
        this.customer = customer;
        this.waitPerson = waitPerson;
        this.food = food;
    }

    // 返回食物
    public Food item() {
        return food;
    }

    public Customer getCustomer() {
        return customer;
    }

    public WaitPerson getWaitPerson() {
        return waitPerson;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", waitPerson=" + waitPerson +
                ", food=" + food +
                '}';
    }
}

/**
 * 装好食物的盘子
 */
class Plate {
    private final Order order; // 盘子对应的订单
    private final Food food; // 盘子对应的食物

    public Plate(Order order, Food food) {
        this.order = order;
        this.food = food;
    }

    public Order getOrder() {
        return order;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return food.toString();
    }
}

/**
 * 顾客类
 */
class Customer implements Runnable {
    private static int counter = 0;
    private final int id = counter++; // 顾客id
    private final WaitPerson waitPerson; // 服务顾客的服务员

    /*顾客面前的盘子，这里使用的是一种没有容量的阻塞队列，每个put()都必须等待一个take()，
    * 反之亦然。本例中假定顾客只点一种食物*/
    private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();

    public Customer(WaitPerson waitPerson) {
        this.waitPerson = waitPerson;
    }

    /*将制作完成的食物给顾客，如果之前已经put()过了，顾客还没有take()，这里就会阻塞*/
    public void deliver(Plate plate) throws InterruptedException {
        placeSetting.put(plate);
    }

    @Override
    public void run() {
        for (Course course : Course.values()) {
            // 顾客随机选择一种食物
            Food food = course.randomSelection();
            try {
                // 服务员提交订单
                waitPerson.placeOrder(this, food);
                // 用户吃食物，如果食物还没好，则会阻塞
                System.out.println(this + " eating " + placeSetting.take());
            } catch (InterruptedException e) {
                System.out.println(this + " interrupted");
                break;
            }
        }
        System.out.println(this + "finished meal, leaving");
    }

    @Override
    public String toString() {
        return "Customer " + id + " ";
    }
}

/**
 * 服务员类
 */
class WaitPerson implements Runnable {
    private static int counter = 0;
    private final int id = counter++; // 服务员id
    private final Restaurant restaurant; // 服务员所属的饭店

    /*无界队列，用来存放厨师已经做好的食物盘子，服务员要将这些盘子送到顾客手上*/
    BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<>();

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // 服务员提交顾客点的食物订单
    public void placeOrder(Customer customer, Food food) {
        try {
            // 创建订单
            Order order = new Order(customer, this, food);
            // 往饭店订单队列中添加订单
            restaurant.orders.put(order);
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 如果队列为空，会阻塞
                Plate plate = filledOrders.take();
                System.out.println(this + "将食物" + plate + "给顾客" + plate.getOrder().getCustomer());
                // 将做好的食物盘子给对应的顾客
                plate.getOrder().getCustomer().deliver(plate);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " finished");
    }

    @Override
    public String toString() {
        return "WaitPerson " + id + " ";
    }
}

/**
 * 厨师类
 */
class Chef implements Runnable {
    private static int counter = 0;
    private final int id = counter++; // 厨师id
    private final Restaurant restaurant; // 厨师所属的饭店
    private static Random random = new Random(47);

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 从订单队列中取出订单，如果队列为空会阻塞
                Order order = restaurant.orders.take();
                Food food = order.item();
                // 花时间制作食物
                System.out.println(this + " 制作服务员 " + order.getWaitPerson() + "给顾客 " + order.getCustomer() + "的食物" + food);
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                Plate plate = new Plate(order, food);
                // 将已经制作好的食物盘子交给订单对应的服务员
                order.getWaitPerson().filledOrders.put(plate);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " finished");
    }

    @Override
    public String toString() {
        return "Chef " + id + " ";
    }
}

/**
 * 饭店类
 */
class Restaurant implements Runnable {
    private List<WaitPerson> waitPersonList = new ArrayList<>(); // 饭店的服务员
    private List<Chef> chefList = new ArrayList<>(); // 饭店里的厨师
    private ExecutorService exec;
    private static Random random = new Random(47);

    BlockingQueue<Order> orders = new LinkedBlockingQueue<>(); // 饭店里的订单队列

    public Restaurant(ExecutorService exec, int nWaitPersons, int nChefs) {
        this.exec = exec;
        // 初始化饭店里的服务员和厨师
        for (int i = 0; i < nWaitPersons; i++) {
            WaitPerson waitPerson = new WaitPerson(this);
            waitPersonList.add(waitPerson);
            exec.execute(waitPerson);
        }

        for (int i = 0; i < nChefs; i++) {
            Chef chef = new Chef(this);
            chefList.add(chef);
            exec.execute(chef);
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 顾客来了，安排服务员服务
                WaitPerson waitPerson = waitPersonList.get(random.nextInt(waitPersonList.size()));
                Customer customer = new Customer(waitPerson);
                exec.execute(customer);
                // 每隔一段时间生产顾客
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Restaurant interrupte");
        }
        System.out.println("Restaurant closing");
    }
}

public class RestaurantWithQueues {
    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Restaurant restaurant = new Restaurant(threadPool, 5, 2);
        threadPool.execute(restaurant);
        System.out.println("Pring 'Enter' to quit");
        System.in.read();
        threadPool.shutdownNow();
    }
}
