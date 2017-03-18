package org.lgc.tij.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程的优先级
 * Created by laigc on 2017/3/18.
 */
public class SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d; // 表示不进行编译器优化
    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        // 注意是在run()方法中开头部分设置的优先级
        // 在构造器中设置不会有任何好处，因为Executor在此刻还没有开始执行任务
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        threadPool.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        // 还有一个线程级别
//        Thread.NORM_PRIORITY;
        threadPool.shutdown();
    }
}
