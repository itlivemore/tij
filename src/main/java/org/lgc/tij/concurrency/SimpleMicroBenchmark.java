package org.lgc.tij.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 简单测试同步操作效率
 * 像本例的测试，只是测试在一个线程中运行，测试结果不准确.复杂一点的测试见SynchronizationComparisons
 * Created by lgc on 17-3-29.
 */
abstract class Incrementable {
    protected long counter = 0;

    // 抽象方法，实现自增
    public abstract void increment();
}

//使用synchronized同步
class SynchronizingTest extends Incrementable {
    @Override
    public synchronized void increment() {
        ++counter;
    }
}

// 使用Lock同步
class LockingTest extends Incrementable {
    private Lock lock = new ReentrantLock();

    @Override
    public void increment() {
        lock.lock();
        try {
            ++counter;
        } finally {
            lock.unlock();
        }
    }
}

public class SimpleMicroBenchmark {
    static long test(Incrementable incrementable) {
        long start = System.nanoTime();
        for (long i = 0; i < 10000000L; i++) {
            incrementable.increment();
        }
        return System.nanoTime() - start;
    }

    public static void main(String[] args) {
        long syncTime = test(new SynchronizingTest());
        long lockTime = test(new LockingTest());
        System.out.println(syncTime);
        System.out.println(lockTime);
        /**输出的结果是
         * 393355808
         * 328189627
         * 看来使用ReentrantLock快些*/
    }
}
