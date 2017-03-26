package org.lgc.tij.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * PriorityBlockingQueue是一个优先级队列，它具有可阻塞的读取操作。
 * 在优先级队列中的对象是按照优先级顺序从队列中出队。
 * Created by laigc on 2017/3/26.
 */

class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
    private static int counter = 0;
    private final int id = counter++;
    private Random random = new Random(47);
    private final int priority; // 优先级
    protected static List<PrioritizedTask> sequence = new ArrayList<>();

    public PrioritizedTask(int priority) {
        this.priority = priority;
        sequence.add(this);
    }

    @Override
    public int compareTo(PrioritizedTask o) {
        return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(250));
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("[%1$-3d]", priority) + " Task " + id;
    }

    public String summary() {
        return "(" + id + ":" + priority + ")";
    }

    public static class EndSentinel extends PrioritizedTask {
        private ExecutorService exec;

        public EndSentinel(ExecutorService exec) {
            // 优先级调成本程序最低值
            super(-1);
            this.exec = exec;
        }

        @Override
        public void run() {
            int count = 0;
            for (PrioritizedTask pt : sequence) {
                System.out.print(pt.summary());
                if (++count % 5 == 0) {
                    System.out.println();
                }
            }
            System.out.println();
            System.out.println(this + " Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

class PrioritizedTaskProducer implements Runnable {
    private Random random = new Random(47);
    private Queue<Runnable> queue;
    private ExecutorService exec;

    public PrioritizedTaskProducer(Queue<Runnable> queue, ExecutorService exec) {
        this.queue = queue;
        this.exec = exec;
    }

    @Override
    public void run() {
        // 无界队列，添加时不会阻塞
        for (int i = 0; i < 20; i++) {
            queue.add(new PrioritizedTask(random.nextInt(10)));
            Thread.yield();
        }

        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(10));
            }
            for (int i = 0; i < 10; i++) {
                queue.add(new PrioritizedTask(i));
            }
            queue.add(new PrioritizedTask.EndSentinel(exec));
        } catch (InterruptedException e) {
            System.out.println("PrioritizedTaskProducer interrupted");
        }
        System.out.println("Finished PrioritizedTaskProducer");
    }
}

class PrioritizedTaskConsumer implements Runnable {
    private PriorityBlockingQueue<Runnable> queue;

    public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                queue.take().run();
            }
        } catch (InterruptedException e) {
            System.out.println("PrioritizedTaskConsumer interrupted");
        }
        System.out.println("Finished PrioritizedTaskConsumer");
    }
}

public class PriorityBlockingQueueDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
        threadPool.execute(new PrioritizedTaskProducer(queue, threadPool));
        threadPool.execute(new PrioritizedTaskConsumer(queue));
    }
}
