package org.lgc.tij.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 验证是否是偶数
 * Created by laigc on 2017/3/18.
 */
public class EvenChecker implements Runnable {
    private IntGenerator intGenerator;
    private final int id;

    public EvenChecker(IntGenerator intGenerator, int id) {
        this.intGenerator = intGenerator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!intGenerator.isCanceled()) {
            int val = intGenerator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even!");
                intGenerator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator, int count) {
        System.out.println("Press Ctrl-C to exit");
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            threadPool.execute(new EvenChecker(generator, i));
        }
        threadPool.shutdown();
    }

    public static void test(IntGenerator generator) {
        test(generator, 10);
    }
}
