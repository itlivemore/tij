package org.lgc.tij.concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 因为不能中断试图执行I/O操作的线程，这里的解决方法是：关闭线程在其上发生阻塞的底层资源
 * Created by laigc on 2017/3/19.
 */
public class CloseResuorce {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InputStream socketInput = new Socket("localhost", 8080).getInputStream();
        threadPool.execute(new IOBlocked(socketInput));
        threadPool.execute(new IOBlocked(System.in));
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Shutting down all threads");
        threadPool.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Closing " + socketInput.getClass().getName());
        socketInput.close();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Closing " + System.in.getClass().getName());
        System.in.close();
    }
}
