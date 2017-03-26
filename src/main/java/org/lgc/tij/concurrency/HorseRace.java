package org.lgc.tij.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * CyclicBarrier适用于这样的情况：你希望创建一组任务，它们并行地执行工作，
 * 然后在进行下一个步骤之前等待，直至所有任务都完成（看起来有些像join()）。
 * 它使得所有的并行任务都将在栅栏处列队，因此可以一致地向前移动。
 * 这非常像CountDownLatch，只是CountDownLatch是只触发一次的事件，
 * 而CyclicBarrier可以多次重用.
 * 本示例是赛马游戏
 * Created by laigc on 2017/3/25.
 */
class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0; // 马走的总路程
    private static Random random = new Random(47);

    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += random.nextInt(3);
                }
                // 一回合走完了，需要等待其它的马走完
                barrier.await();
            }
        } catch (InterruptedException e) {
            System.out.println("Horse interrupted");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Horse " + id + " ";
    }

    public String tracks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }
}

public class HorseRace {
    static final int FINISH_LINE = 75;

    private List<Horse> horses = new ArrayList<>();

    private ExecutorService exec = Executors.newCachedThreadPool();

    private CyclicBarrier barrier;

    public HorseRace(int nHorses, final int pause) {
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            //全部马都走完一回合后会自动调用下面定义的任务
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++) {
                    sb.append("="); // 新的回合
                }
                System.out.println(sb);
                for (Horse horse : horses) {
                    System.out.println(horse.tracks());
                }
                for (Horse horse : horses) {
                    if (horse.getStrides() >= FINISH_LINE) {
                        System.out.println(horse + " won!");
                        exec.shutdownNow();
                        return;
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    System.out.println("barrier-action sleep interrupted");
                }
            }
        });

        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorses = 7;
        int pause = 200;
        new HorseRace(nHorses, pause);
    }
}
