package org.lgc.tij.concurrency;

/**
 * 使用Thread类来启动线程
 * Created by laigc on 2017/3/18.
 */
public class BasicThreads {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff");
    }
}
