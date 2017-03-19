package org.lgc.tij.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试原子性
 * 尽管evenIncrement()加了synchronized，但是缺少同步操作使得getValue()会在evenIncrement()执行完后才执行
 * Created by laigc on 2017/3/19.
 */
public class AtomicityTest implements Runnable {
    private int i = 0;

    public int getValue() {
        return i;
    }

    private synchronized void evenIncrement() {
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        AtomicityTest atomicityTest = new AtomicityTest();
        threadPool.execute(atomicityTest);
        while (true) {
            int val = atomicityTest.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
