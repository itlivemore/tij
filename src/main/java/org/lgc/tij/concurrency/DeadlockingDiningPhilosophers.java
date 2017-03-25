package org.lgc.tij.concurrency;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 哲学家就餐问题死锁版本
 * Created by laigc on 2017/3/25.
 */
public class DeadlockingDiningPhilosophers {
    public static void main(String[] args) throws IOException {
        int ponder = 1;
        int size = 5;

        ExecutorService threadPool = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            threadPool.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) % size], i, ponder));
        }
        System.out.println("Press 'Enter' to quit");
        System.in.read();
        threadPool.shutdownNow();
    }
}
