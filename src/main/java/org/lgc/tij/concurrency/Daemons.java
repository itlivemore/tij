package org.lgc.tij.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 如果是一个后台线程，那么它创建的任何线程将自动设置成后台线程
 * 可以通过调用isDaemon()方法来确定线程是否是一个后台线程
 * Created by laigc on 2017/3/18.
 */
class Daemon implements Runnable {
    private Thread[] threads = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new DaemonSpawn());
            threads[i].start();
            System.out.println("DaemonSpawn " + i + " started. ");
        }
        for (int i = 0; i < threads.length; i++) {
            System.out.println("threads[" + i + "].isDaemon()=" + threads[i].isDaemon());
        }
        while (true) {
            Thread.yield();
        }
    }
}

class DaemonSpawn implements Runnable {
    @Override
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}

public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Daemon());
        thread.setDaemon(true);
        thread.start();
        System.out.println("thread.isDaemon() = " + thread.isDaemon());
        TimeUnit.MILLISECONDS.sleep(1000);
    }
}
