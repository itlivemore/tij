package org.lgc.tij.concurrency;

/**
 * 调用LiftOff
 * Created by laigc on 2017/3/18.
 */
public class MainThread {
    public static void main(String[] args) {
        LiftOff liftOff = new LiftOff();
        liftOff.run();
    }
}
