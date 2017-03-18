package org.lgc.tij.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程
 * 所谓后台线程，是指在程序运行的时候在后台提供一种通用服务的线程，
 * 并且这种线程并不属于程序中不可或缺的一部分。
 * 因此，当所有的非后台线程结束时，程序也就终止了，同时会杀死进程中的所有后台线程。
 * 反过来说，只要有任何非后台线程还在执行，程序就不会终止
 * 比如，执行main()的就是一个非后台线程
 * Created by laigc on 2017/3/18.
 */
public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            // 必须在线程启动之前调用setDaemon()方法，才能把它设置为后台线程
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
