package org.lgc.tij.concurrency;

/**
 * 哲学家就餐问题中的筷子
 * Created by laigc on 2017/3/25.
 */
public class Chopstick {
    // 是否在使用
    private boolean taken = false;

    public synchronized void taken() throws InterruptedException {
        while (taken) {
            wait();
        }
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
