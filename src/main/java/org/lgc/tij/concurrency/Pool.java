package org.lgc.tij.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 正常的锁在任何时刻都只允许一个任务访问一项资源,而计数信号量(Semaphore)允许n个任务同时访问这个资源。
 * 你还可以将信号量看作是向外分发使用资源的“许可证”，尽管实际上没有使用任何许可证对象。
 * 本例是一个对象池，它管理着数量有限的对象，当要使用对象时可以签出它们，而在用户使用完毕时，可以将它们签回.
 * Created by laigc on 2017/3/26.
 */
public class Pool<T> {
    private int size;
    private List<T> items = new ArrayList<T>();
    private volatile boolean[] checkedOut; // 是否被签出去了
    private Semaphore available;

    public Pool(Class<T> tClass, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        available = new Semaphore(size, true);
        // 添加对象
        for (int i = 0; i < size; i++) {
            try {
                items.add(tClass.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 签出对象
    public T checkOut() throws InterruptedException {
        // 如果没有任何信号量许可证可用，将阻塞
        available.acquire();
        return getItem();
    }

    // 签入对象
    public void checkIn(T x) {
        if (releaseItem(x)) {
            available.release();
        }
    }

    private synchronized T getItem() {
        for (int i = 0; i < size; i++) {
            // 如果没有签出
            if (!checkedOut[i]) {
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }

    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if (index == -1) {
            // 释放的对象不在列表中
            return false;
        }
        if (checkedOut[index]) {
            checkedOut[index] = false;
            return true;
        }
        return false; // 没有被签出去
    }

}
