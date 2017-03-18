package org.lgc.tij.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 本例的任务总是会抛出一个异常，该异常会传播到其run()方法的外部
 * 用try-catch也无法捕捉到
 * Created by laigc on 2017/3/18.
 */
public class ExceptionThread implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        try {
            ExecutorService threadPool = Executors.newCachedThreadPool();
            threadPool.execute(new ExceptionThread());
        } catch (Exception e) {
            System.out.println("exception has been handled");
        }
    }
}
