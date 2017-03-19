package org.lgc.tij.concurrency;

/**
 * 两个任务可以同时进入同一个对象，只要这个对象上的方法是在不同的锁上同步的即可
 * Created by laigc on 2017/3/19.
 */
class DualSynch {
    private Object syncObject = new Object();

    // 锁对象是this,和下面的g()方法是互相独立的
    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }

    public void g() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}

public class SyncObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread() {
            @Override
            public void run() {
                ds.f();
            }
        }.start();
        ds.g();
    }
}
