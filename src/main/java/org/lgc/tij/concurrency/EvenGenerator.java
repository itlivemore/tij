package org.lgc.tij.concurrency;

/**
 * 生成int,并测试是否是偶数
 * Created by laigc on 2017/3/19.
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    @Override
    public int next() {
        // 多线程访问时会有问题,下面的语句不能保证同时都执行而不被其他线程中断
        ++currentEvenValue;
        Thread.yield(); // 加入这一句会使得不能同时执行的概率增加
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator(), 100);
    }
}
