package org.lgc.tij.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 使用BlockingQueue的示例，有一台机器具有三个任务：一个制作吐司，一个给吐司抹黄油，
 * 另一个在抹过黄油的吐司上涂果酱
 * Created by laigc on 2017/3/25.
 */
class Toast {
    public enum Status {
        DRY, BUTTERED, JAMMED
    }

    private Status status = Status.DRY;

    private final int id;

    public Toast(int id) {
        this.id = id;
    }

    public void butter() {
        status = Status.BUTTERED;
    }

    public void jam() {
        status = Status.JAMMED;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Toast{" +
                "status=" + status +
                ", id=" + id +
                '}';
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {
}

class Toaster implements Runnable {
    private ToastQueue toastQueue;
    private int count = 0;
    private Random random = new Random(47);

    public Toaster(ToastQueue toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
                // 制作吐司
                Toast toast = new Toast(count++);
                System.out.println("Make toast " + toast);
                // 加入到队列
                toastQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted");
        }
        System.out.println("Toaster off");
    }
}

// 涂抹黄油
class Butterer implements Runnable {
    private ToastQueue dryQueue, butteredQueue;

    public Butterer(ToastQueue dryQueue, ToastQueue butteredQueue) {
        this.dryQueue = dryQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = dryQueue.take();
                toast.butter();
                System.out.println("butter " + toast);
                butteredQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer interrpted");
        }
        System.out.println("Butterer off");
    }
}

// 涂果酱
class Jammer implements Runnable {
    private ToastQueue butteredQueue, finishedQueue;

    public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue) {
        this.butteredQueue = butteredQueue;
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = butteredQueue.take();
                toast.jam();
                System.out.println("jammed " + toast);
                finishedQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Jammer interrputed");
        }
        System.out.println("Jammer off");
    }
}

// 消费吐司
class Eater implements Runnable {
    private ToastQueue finishedQueue;
    private int counter = 0;

    public Eater(ToastQueue finishedQueue) {
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = finishedQueue.take();
                if (toast.getId() != counter++ || toast.getStatus() != Toast.Status.JAMMED) {
                    System.out.println(">>>> Error: " + toast);
                    System.exit(1);
                } else {
                    System.out.println("Eat " + toast);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }
}

public class ToastOMatic {
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue(), butteredQueue = new ToastQueue(), finishedQueue = new ToastQueue();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(new Toaster(dryQueue));
        threadPool.execute(new Butterer(dryQueue, butteredQueue));
        threadPool.execute(new Jammer(butteredQueue, finishedQueue));
        threadPool.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        threadPool.shutdownNow();
    }
}
