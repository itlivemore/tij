package org.lgc.tij.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用AtomicInteger重写MutexEvenGererator
 * Created by laigc on 2017/3/19.
 */
public class AtomicEvenGererator extends IntGenerator {
    private AtomicInteger currentEvenValue = new AtomicInteger(0);

    @Override
    public int next() {
        return currentEvenValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGererator());
    }
}
