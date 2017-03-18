package org.lgc.tij.concurrency;

/**
 * 实现Runnable接口
 * Created by laigc on 2017/3/18.
 */
public class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "). ";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            // 将CPU从一个线程转移给另一个线程。
            // 它在声明：“我已经执行完生命周期中最重要的部分了，此刻正是切换给其他任务执行一段时间的大好时机”
            Thread.yield();
        }
    }
}
