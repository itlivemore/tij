package org.lgc.tij.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 * 可以同时有多个读者，只要它们都不试图写入即可。
 * 如果写锁已经被其他任务持有，那么任何读取者都不能访问，直至这个写锁被释放为止。
 * Created by laigc on 2017/4/2.
 */
public class ReaderWriterList<T> {
    private ArrayList<T> lockedList;

    // 使顺序公平
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public ReaderWriterList(int size, T initialValue) {
        lockedList = new ArrayList<T>(Collections.nCopies(size, initialValue));
    }

    public T set(int index, T element) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            return lockedList.set(index, element);
        } finally {
            writeLock.unlock();
        }
    }

    public T get(int index) {
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            // 看下有多少个读取者
            if (lock.getReadLockCount() > 1) {
                System.out.println(lock.getReadLockCount());
            }
            return lockedList.get(index);
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        new ReaderWriterListTest(30, 1);
    }
}

class ReaderWriterListTest {
    ExecutorService exec = Executors.newCachedThreadPool();

    private final static int SIZE = 100;
    private static Random random = new Random(47);

    private ReaderWriterList<Integer> list = new ReaderWriterList<>(SIZE, 0);

    private class Writer implements Runnable {
        @Override
        public void run() {
            try {
                // 执行2秒钟
                for (int i = 0; i < 20; i++) {
                    list.set(i, random.nextInt());
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("Writer interrupted");
            }
            System.out.println("Writer finished, shutting down");
            // 有点奇怪，此处执行了关闭线程池，Reader已经interrupted，但是Reader线程还在运行，程序没有终止
            exec.shutdownNow();
        }
    }

    private class Reader implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    System.out.println(Thread.currentThread() + " read data");
                    for (int i = 0; i < SIZE; i++) {
                        list.get(i);
                        TimeUnit.MILLISECONDS.sleep(1);
                    }
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread() + " Reader interrupted");
                }
            }
        }
    }

    public ReaderWriterListTest(int readers, int writers) {
        for (int i = 0; i < readers; i++) {
            exec.execute(new Reader());
        }
        for (int i = 0; i < writers; i++) {
            exec.execute(new Writer());
        }
    }
}
