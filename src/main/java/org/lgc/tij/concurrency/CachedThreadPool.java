package org.lgc.tij.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用Executor启动线程
 * Created by laigc on 2017/3/18.
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = null;
        threadPool = Executors.newCachedThreadPool();
        threadPool = Executors.newFixedThreadPool(5);
        threadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(new LiftOff());
        }
        threadPool.shutdown();
    }
}
