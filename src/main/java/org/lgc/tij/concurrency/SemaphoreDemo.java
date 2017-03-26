package org.lgc.tij.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 测试Pool
 * 创建一个任务，它将签出Fat对象，持有一段时间之后再将它们签入.
 * Created by laigc on 2017/3/26.
 */
class CheckoutTask<T> implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private Pool<T> pool;

    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            // 签出
            T item = pool.checkOut();
            System.out.println(this + " checked out " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + " checking in " + item);
            // 签入
            pool.checkIn(item);
        } catch (InterruptedException e) {
            System.out.println("CheckoutTask " + id + " interrupted");
        }
    }

    @Override
    public String toString() {
        return "CheckoutTask " + id + " ";
    }
}

public class SemaphoreDemo {
    final static int SIZE = 25;

    public static void main(String[] args) throws InterruptedException {
        final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new CheckoutTask<>(pool));
        }
        System.out.println("All CheckoutTasks created");
        List<Fat> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Fat fat = pool.checkOut();
            // 主线程签出
            System.out.print(i + ": main() thread checked out ");
            fat.operation();
            // 先持有着,不签回，这里将会持有对象池中所有对象，其它线程将不能从对象池中签出对象
            list.add(fat);
        }
        // 上一步把对象池中的所有对象都签出了，所以这里会被阻塞
        Future<?> blocked = exec.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    pool.checkOut();
                } catch (InterruptedException e) {
                    System.out.println("checkOut() Interrupted");
                }
            }
        });
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);
        System.out.println("Checking in objects in " + list);
        for (Fat fat : list) {
            pool.checkIn(fat);
        }
        for (Fat fat : list) {
            pool.checkIn(fat); // 第二次签回，将被忽略
        }
        exec.shutdownNow();
    }
}
