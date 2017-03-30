package org.lgc.tij.concurrency;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发测试synchronized和lock
 * Created by lgc on 17-3-30.
 */
abstract class Accumulator {
    public static long cycles = 50000L;

    private static final int N = 4; // Modifier和Reader线程数量

    public static ExecutorService exec = Executors.newFixedThreadPool(N * 2);

    /*因为有Modifier和Reader，每个各开N个线程，测试钱程等待计时，需要Modifier和Reader运算完成，
    所以barrier的计数是N*2+1*/
    private static CyclicBarrier barrier = new CyclicBarrier(N * 2 + 1);

    protected volatile int index = 0;

    protected volatile long value = 0;

    protected long duration = 0; // 时长

    protected String id = "error";

    protected final static int SIZE = 100000;

    protected static int[] preLoaded = new int[SIZE]; // 存放随机数的数组，计算时从这个数组中取出值

    static {
        // 随机数组赋值
        Random random = new Random(47);
        for (int i = 0; i < SIZE; i++) {
            preLoaded[i] = random.nextInt();
        }
    }

    // 累加计算
    public abstract void accumulate();

    // 读取计算结果
    public abstract long read();

    private class Modifier implements Runnable {

        @Override
        public void run() {
            // 循环调用计算方法accumulate()
            for (int i = 0; i < cycles; i++) {
                accumulate();
            }
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class Reader implements Runnable {
        private volatile long value;

        @Override
        public void run() {
            for (int i = 0; i < cycles; i++) {
                value = read();
            }
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void timedTest() {
        long start = System.nanoTime();
        // 启动N个线程
        for (int i = 0; i < N; i++) {
            exec.execute(new Modifier());
            exec.execute(new Reader());
        }
        try {
            // 等待计算完成
            barrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        duration = System.nanoTime() - start;
        System.out.printf("%-13s: %13d\n", id, duration);
    }

    public static void report(Accumulator acc1, Accumulator acc2) {
        System.out.printf("%-22s: %.2f\n", acc1.id + "/" + acc2.id,
                (double) acc1.duration / (double) acc2.duration);
    }
}

// 不使用同步
class BaseLine extends Accumulator {
    {
        id = "BaseLine";
    }

    @Override
    public void accumulate() {
        // 不使用同步这里会报错,当一个线程到了SIZE还未将值设置为0,而另一个线程访问index++，则数组越界了
//        value += preLoaded[index++];
        value += preLoaded[(index++) % SIZE];
        if (index >= SIZE) {
            index = 0;
        }
    }

    @Override
    public long read() {
        return value;
    }
}

// 使用synchronized
class SynchronizedTest extends Accumulator {
    {
        id = "synchronized";
    }

    @Override
    public synchronized void accumulate() {
        value += preLoaded[index++];
        if (index >= SIZE) {
            index = 0;
        }
    }

    @Override
    public synchronized long read() {
        return value;
    }
}

// 使用Lock
class LockTest extends Accumulator {
    {
        id = "Lock";
    }

    private Lock lock = new ReentrantLock();

    @Override
    public void accumulate() {
        lock.lock();
        try {
            value += preLoaded[index++];
            if (index >= SIZE) {
                index = 0;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long read() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }
}

// 使用Atomic
class AtomicTest extends Accumulator {
    {
        id = "Atomic";
    }

    private AtomicInteger index = new AtomicInteger(0);

    private AtomicLong value = new AtomicLong(0);

    @Override
    public void accumulate() {
        //并发下，同一时刻只使用一个Atomic并不能很好地工作，但是它仍然能给我们一个性能指标
        int i = index.getAndIncrement();
//        value.getAndAdd(preLoaded[i]);
        value.getAndAdd(preLoaded[i % SIZE]);
        if (++i >= SIZE) {
            index.set(0);
        }
    }

    @Override
    public long read() {
        return value.get();
    }
}

public class SynchronizationComparisons {
    static BaseLine baseLine = new BaseLine();
    static SynchronizedTest syncTest = new SynchronizedTest();
    static LockTest lockTest = new LockTest();
    static AtomicTest atomicTest = new AtomicTest();

    static void test() {
        System.out.println("========================");
        System.out.printf("%-12s : %13d\n", "Cycles", Accumulator.cycles);
        baseLine.timedTest();
        syncTest.timedTest();
        syncTest.timedTest();
        lockTest.timedTest();
        atomicTest.timedTest();

        Accumulator.report(syncTest, baseLine);
        Accumulator.report(lockTest, baseLine);
        Accumulator.report(atomicTest, baseLine);
        Accumulator.report(syncTest, lockTest);
        Accumulator.report(syncTest, atomicTest);
        Accumulator.report(lockTest, atomicTest);
    }

    public static void main(String[] args) {
        int iterations = 5;
        System.out.println("Warmup");
        baseLine.timedTest();
        for (int i = 0; i < iterations; i++) {
            test();
            Accumulator.cycles *= 2;
        }
        Accumulator.exec.shutdown();
    }
}
















