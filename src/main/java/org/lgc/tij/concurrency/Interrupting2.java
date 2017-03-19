package org.lgc.tij.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock上阻塞的任务具备可以被中断的能力
 * Created by laigc on 2017/3/19.
 */
class BlockedMutex {
    private Lock lock = new ReentrantLock();

    public BlockedMutex() {
        // 获得锁而且不释放
        lock.lock();
    }

    public void f() {
        try {
            lock.lockInterruptibly();
            System.out.println("lock acquired in f()");
        } catch (InterruptedException e) {
            System.out.println("Interrupted form lock acquisition in f()");
        }
    }
}

class Blocked2 implements Runnable {
    BlockedMutex blockedMutex = new BlockedMutex();

    @Override
    public void run() {
        System.out.println("Waiting for f() in BlockedMutex");
        // 调用f()会被阻塞
        blockedMutex.f();
        System.out.println("Broken out of blocked call");
    }
}

public class Interrupting2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Blocked2());
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing thread.interrupt()");
        thread.interrupt();
    }
}
