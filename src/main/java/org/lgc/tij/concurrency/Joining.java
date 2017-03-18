package org.lgc.tij.concurrency;

/**
 * join()
 * 一个线程可以在其他线程之上调用join()方法，其效果是等待一段时间直到第二个线程结束才能执行。
 * 如果某个线程在另一个线程t上调用t.join()，此线程将被挂起，直到目标线程t结束才恢复（即t.isAlive()返回为假）
 * 也可以在调用join()时带上一个超时参数（单位可以是毫秒，或者是纳秒），这样如果目标线程在这段时间到期时还没有
 * 结束的话，join()方法总能返回。
 * 对join()方法调用可以被中断，做法是在调用线程上调用，做法是在线程上调用interrupt()方法，这时需要用到try-catch子句
 * Created by laigc on 2017/3/18.
 */
class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted, isInterrupted(): " + isInterrupted());
            return;
        }

        System.out.println(getName() + " has awakened");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println(getName() + " join completed");
    }
}

public class Joining {
    public static void main(String[] args) {
        Sleeper sleepy = new Sleeper("Sleepy", 1500);
        Sleeper grumpy = new Sleeper("Grumpy", 1500);

        Joiner dopey = new Joiner("Dopey", sleepy);
        Joiner doc = new Joiner("doc", grumpy);
        grumpy.interrupt();
    }
}
