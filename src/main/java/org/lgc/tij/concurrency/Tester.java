package org.lgc.tij.concurrency;

import net.mindview.util.Generated;
import net.mindview.util.RandomGenerator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 容器测试类,泛型参数C表示容器的类型
 * Created by lgc on 17-3-31.
 */
public abstract class Tester<C> {
    static int testReps = 10; // 测试重复的次数
    static int testCycles = 1000;
    static int containerSize = 1000;

    abstract C containerInitializer(); // 初始化容器

    abstract void startReaderAndWriters(); // 启动读取者与写入者任务

    C testContainer; // 测试的容器，由containerInitializer()返回

    String testId;

    int nReaders;

    int nWriters;

    volatile long readResult = 0;
    volatile long readTime = 0;
    volatile long writeTime = 0;

    CountDownLatch endLatch;

    static ExecutorService exec = Executors.newCachedThreadPool();

    Integer[] writeData;

    Tester(String testId, int nReaders, int nWriters) {
        this.testId = testId + " " + nReaders + "r " + nWriters + "w";
        this.nReaders = nReaders;
        this.nWriters = nWriters;

        writeData = Generated.array(Integer.class, new RandomGenerator.Integer(), containerSize);

        for (int i = 0; i < testReps; i++) {
            runTest();
            readTime = 0;
            writeTime = 0;
        }
    }

    void runTest() {
        endLatch = new CountDownLatch(nReaders + nWriters);
        testContainer = containerInitializer();
        startReaderAndWriters();
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            System.out.println("endLatch interrupted");
        }
        System.out.printf("%-27s %14d %14d\n", testId, readTime, writeTime);
        if (readTime != 0 && writeTime != 0) {
            System.out.printf("%-27s %14d\n", "readTime + writeTime", readTime + writeTime);
        }
    }

    // Reader和Writer将要继承的类
    abstract class TestTask implements Runnable {
        abstract void test();

        abstract void putResult();

        long duration;

        @Override
        public void run() {
            long start = System.nanoTime();
            test();
            duration = System.nanoTime() - start;
            synchronized (Tester.this) {
                putResult();
            }
            endLatch.countDown();
        }
    }

    public static void initMain(String[] args) {
        if (args.length > 0) {
            testReps = new Integer(args[0]);
        }
        if (args.length > 1) {
            testCycles = new Integer(args[1]);
        }
        if (args.length > 2) {
            containerSize = new Integer(args[2]);
        }
        System.out.printf("%-27s %14s %14s\n", "Type", "Read time", "Write time");
    }
}
