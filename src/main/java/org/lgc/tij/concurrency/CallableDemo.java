package org.lgc.tij.concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 从线程中产生返回值
 * Created by laigc on 2017/3/18.
 */
class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult " + id;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(threadPool.submit(new TaskWithResult(i)));
        }
        for (Future<String> future : results) {
            try {
                // 若线程还没有执行完，没有返回结果，future.get()将阻塞
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                threadPool.shutdown();
            }
        }
    }
}
