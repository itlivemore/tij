package org.lgc.tij.concurrency;

/**
 * 同步控制EvenGenerator
 * Created by laigc on 2017/3/19.
 */
public class SynchronizedEvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield(); // 因为加了synchronized，这里也不会造成失败
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }

}
