package org.lgc.tij.concurrency;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过修改最后一个哲学家先拿左边的筷子，后拿右边的筷子来避免死锁。
 * 这样这个哲学家将永远不会阻止其右边的哲学家拿起筷子
 * Created by laigc on 2017/3/25.
 */
public class FixedDiningPhilosophers {
    public static void main(String[] args) throws IOException {
        int ponder = 1;
        int size = 5;

        ExecutorService threadPool = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            if (i < (size - 1)) {
                threadPool.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1)], i, ponder));
            } else {
                threadPool.execute(new Philosopher(chopsticks[0], chopsticks[i], i, ponder));
            }
        }
        System.out.println("Press 'Enter' to quit");
        System.in.read();
        threadPool.shutdownNow();
    }
}
