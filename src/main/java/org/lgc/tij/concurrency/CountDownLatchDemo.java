package org.lgc.tij.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch被用来同步一个或多个任务，强制它们等待由其他任务执行的一组操作完成。
 * 典型用法是将一个程序分为n个互相独立的可解决任务，并创建值为0的CountDownLatch。
 * 当每个任务完成时，都会在这个锁存器上调用countDown()。
 * 等待问题被解决的任务在这个锁存器上调用await()，将它们自己拦住，直至锁存器计数结束。
 * Created by laigc on 2017/3/25.
 */

// 任务类
class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter++;

    private static Random random = new Random(47);
    private final CountDownLatch latch;

    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        System.out.println(this + " completed");
    }

    @Override
    public String toString() {
        return String.format("%1$-3d ", id);
    }
}

// 等待任务执行完的类
class WaitingTask implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;

    public WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("Latch barrier passed for " + this);
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
    }

    @Override
    public String toString() {
        return String.format("WaitingTask %1$-3d ", id);
    }
}

public class CountDownLatchDemo {
    static final int SIZE = 100;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new WaitingTask(latch));
        }
        for (int i = 0; i < SIZE; i++) {
            threadPool.execute(new TaskPortion(latch));
        }
        System.out.println("Launched all tasks");
        threadPool.shutdown();
    }
}
