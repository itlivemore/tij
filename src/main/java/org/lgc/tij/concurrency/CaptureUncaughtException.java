package org.lgc.tij.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 捕获线程中的异常
 * Created by laigc on 2017/3/18.
 */
class ExceptionThread2 implements Runnable {
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println("run() by " + thread);
        System.out.println("eh = " + thread.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

/**
 * 线程异常捕获处理类，实现了Thread.UncaughtExceptionHandler接口
 */
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

class HandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread thread = new Thread(r);
        System.out.println("created " + thread);
        // 设置异常处理类
        thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("HandlerThreadFactory eh = " + thread.getUncaughtExceptionHandler());
        return thread;
    }
}

public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool(new HandlerThreadFactory());
        threadPool.execute(new ExceptionThread2());
    }
}
