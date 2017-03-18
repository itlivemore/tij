package org.lgc.tij.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程在不执行finally子句的情况下就会终止run()方法
 * Created by laigc on 2017/3/18.
 */
class ADaemon implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Staring Daemon");
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("run finally");
        }
    }
}

public class DaemonsDontRunFinally {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ADaemon());
        // 如果注释掉下面一行，就会看到finally执行
        thread.setDaemon(true);
        thread.start();
    }
}
