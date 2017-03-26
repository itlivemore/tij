package org.lgc.tij.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * DelayQueue是一个无界的BlockingQueue,用于放置实现了Delayed接口的对象,其中的对象只能在其到时才能从队列中取走。
 * 这种队列是有序的，即队头对象的延迟到期的时间最长。
 * 如果没有任何延迟到期，那么就不会有任何头元素，并且poll()将返回null(正因为这样，你不能将null放置到这种队列中).
 * 本例中，Delayed对象自身就是任务，而DelayedTaskConsumer将最“紧急”的任务（到期时间最长的任务）从队列中取出，
 * 然后运行它。注意，这样DelayQueue就成为了优先级队列的一种变体
 * Created by laigc on 2017/3/25.
 */
class DelayedTask implements Runnable, Delayed {
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;

    protected static List<DelayedTask> sequence = new ArrayList<>();

    public DelayedTask(int delayInMilliseconds) {
        delta = delayInMilliseconds;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        sequence.add(this);
    }

    // 接口Delayed中的方法，可以用来告知延迟到期有多长时间，或者延迟在多长时间之前已经到期
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    //接口Delayed继承了Comparable接口
    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask) o;
        if (trigger < that.trigger) {
            return -1;
        }
        if (trigger > that.trigger) {
            return 1;
        }
        return 0;
    }

    @Override
    public void run() {
        System.out.print(this + " ");
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]", delta) + " Task " + id;
    }

    public String summary() {
        return "(" + id + ":" + delta + ")";
    }

    public static class EndSentinel extends DelayedTask {
        private ExecutorService exec;

        public EndSentinel(int delayInMilliseconds, ExecutorService exec) {
            super(delayInMilliseconds);
            this.exec = exec;
        }

        @Override
        public void run() {
            System.out.println("\n创建顺序：");
            for (DelayedTask delayedTask : sequence) {
                // 这里打印的是创建的顺序，使用DelayQueue顺序见下面
                System.out.print(delayedTask.summary() + " ");
            }
            System.out.println();
            System.out.println(this + " Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

class DelayedTaskConsumer implements Runnable {
    private DelayQueue<DelayedTask> queue;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("队列顺序：");
            while (!Thread.interrupted()) {
                queue.take().run(); // 这里是按队列中的顺序执行的
            }
        } catch (InterruptedException e) {
            System.out.println("DelayedTaskConsumer interrupted");
        }
        System.out.println("Finished DelayedTaskConsumer");
    }
}

public class DelayQueueDemo {
    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        DelayQueue queue = new DelayQueue();
        for (int i = 0; i < 20; i++) {
            queue.put(new DelayedTask(random.nextInt(5000)));
        }
        // 添加队列最后一个元素，带有线程池关闭功能
        queue.add(new DelayedTask.EndSentinel(5000, threadPool));
        threadPool.execute(new DelayedTaskConsumer(queue));
    }
}
