package org.lgc.tij.concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Executors基本的interrupt()用法
 * 本例中有三种不同类型的阻塞
 * SleepBlock是可中断的阻塞，IOBlocked和SynchronizedBlocked是不可中断的阻塞
 * Created by laigc on 2017/3/19.
 */
class SleepBlocked implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("SleepBlocked.run.InterruptedException ");
        }
        System.out.println("Exiting SleepBloked.run()");
    }
}

class IOBlocked implements Runnable {
    private InputStream in;

    public IOBlocked(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for read:");
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted from blocked I/O");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exiting IOBlocked.run()");
    }
}

class SynchronizedBlocked implements Runnable {
    public synchronized void f() {
        while (true) {
            Thread.yield();
        }
    }

    public SynchronizedBlocked() {
        // 这里启动了一个线程，将会获得对象锁，而f()方法会一直执行，所以对象锁一直被这个线程占用着
        // 所以下面的SynchonizedBlocked的run()方法一直是在试图获得锁
        new Thread() {
            @Override
            public void run() {
                f();
            }
        }.start();
    }

    @Override
    public void run() {
        System.out.println("Trying to call f()");
        f();
        System.out.println("Exiting SynchronizedBlocked.run()");
    }
}

public class Interrupting {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable runnable) throws InterruptedException {
        Future<?> future = exec.submit(runnable);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting " + runnable.getClass().getName());
        future.cancel(true);
        System.out.println("Interrupt sent to " + runnable.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);
    }
}
