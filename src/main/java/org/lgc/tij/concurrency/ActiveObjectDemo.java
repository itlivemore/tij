package org.lgc.tij.concurrency;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 活动对象
 * 每个对象都维护着它自己的工作器线程和消息队列，并且所有对这种对象的请求都将进入队列排队，
 * 任何时刻都只能运行其中的一个。
 * Created by laigc on 2017/4/2.
 */
public class ActiveObjectDemo {
    // 单线程池
    private ExecutorService exec = Executors.newSingleThreadExecutor();

    private Random random = new Random(47);

    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(factor));
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }

    public Future<Integer> caculateInt(final int x, final int y) {
        return exec.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("starting " + x + " + " + y);
                pause(500);
                return x + y;
            }
        });
    }

    public Future<Float> caculateFloat(final float x, final float y) {
        return exec.submit(new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                System.out.println("starting " + x + " + " + y);
                pause(2000);
                return x + y;
            }
        });
    }

    public void shutdown() {
        exec.shutdown();
    }

    public static void main(String[] args) {
        ActiveObjectDemo activeObjectDemo = new ActiveObjectDemo();
        // 因为遍历的时候要删除，所以这里用了CopyOnWriteArrayList
        List<Future<?>> results = new CopyOnWriteArrayList<>();

        for (float f = 0.0f; f < 1.0f; f += 0.2f) {
            results.add(activeObjectDemo.caculateFloat(f, f));
        }

        for (int i = 0; i < 5; i++) {
            results.add(activeObjectDemo.caculateInt(i, i));
        }

        System.out.println("All asynch calls made");
        while (results.size() > 0) {
            for (Future<?> future : results) {
                if (future.isDone()) {
                    try {
                        System.out.println(future.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    results.remove(future);
                }
            }
        }
        activeObjectDemo.shutdown();
    }
}
