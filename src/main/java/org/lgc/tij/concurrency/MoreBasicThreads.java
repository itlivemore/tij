package org.lgc.tij.concurrency;

/**
 * 添加多个线程运行
 * Created by laigc on 2017/3/18.
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for LiftOff");
    }
}
