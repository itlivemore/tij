package org.lgc.tij.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 如果将要在代码中处处使用相同的异常处理器，那么更简单的方式是在Thread类中设置一个静态域，
 * 并将这个处理器设置为默认的未捕获异常处理器
 * Created by laigc on 2017/3/18.
 */
public class SettingDefaultHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService threadPool = Executors.newCachedThreadPool(new HandlerThreadFactory());
        threadPool.execute(new ExceptionThread());
    }
}
