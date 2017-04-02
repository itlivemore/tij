package org.lgc.tij.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 乐观加锁
 * 假设当前线程修改某个值时，该值不会被其他线程修改。
 * 假设修改前原值是a，修改线程保留一个修改前原始值b，此时a==b，将要修改值时判断b是否与当前值是否相等。
 * 假设没有其他线程修改，则会相等，如果有其它线程修改了，值不会相等，此时需要根据具体情况处理。
 * 本例中用修改二维数组中的值，AtomicInteger使用乐观加锁，compareAndSet()方法判断是否有其它线程修改了值。
 * Created by laigc on 2017/4/2.
 */
public class FastSimulation {
    static final int N_ELEMENTS = 100000;
    static final int N_GENES = 30;
    static final int N_EVOLVERS = 50;
    static final AtomicInteger[][] GRID = new AtomicInteger[N_ELEMENTS][N_GENES];
    static Random random = new Random(47);

    static class Evolver implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                // 随机选择一个元素
                int element = random.nextInt(N_ELEMENTS);
                for (int i = 0; i < N_GENES; i++) {
                    int previous = element - 1;
                    if (previous < 0) {
                        previous = N_EVOLVERS - 1;
                    }
                    int next = element + 1;
                    if (next >= N_ELEMENTS) {
                        next = 0;
                    }
                    int oldvalue = GRID[element][i].get();
                    int newvalue = oldvalue + GRID[previous][i].get() + GRID[next][i].get();
                    newvalue /= 3;
                    // 判断原来的值是否被其它线程修改
                    if (!GRID[element][i].compareAndSet(oldvalue, newvalue)) {
                        System.out.println("Old value changed from " + oldvalue);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < N_ELEMENTS; i++) {
            for (int j = 0; j < N_GENES; j++) {
                GRID[i][j] = new AtomicInteger(random.nextInt(1000));
            }
        }
        for (int i = 0; i < N_EVOLVERS; i++) {
            threadPool.execute(new Evolver());
        }
        TimeUnit.SECONDS.sleep(5);
        threadPool.shutdownNow();
    }

}
