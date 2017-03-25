package org.lgc.tij.concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 哲学家就餐问题中的哲学家
 * Created by laigc on 2017/3/25.
 */
public class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor; // 随机休眠时间数
    private Random random = new Random(47);

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
    }

    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 思考
                System.out.println(this + " " + "thinking");
                pause();
                // 开始吃饭
                System.out.println(this + " grabbing right");
                right.taken();
                System.out.println(this + " grabbing left");
                left.taken();
                System.out.println(this + " eating");
                pause();
                right.drop();
                left.drop();
            }
        } catch (InterruptedException e) {
            System.out.println(this + " exiting interrupt");
        }
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}
