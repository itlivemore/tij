package org.lgc.tij.concurrency;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * nio类提供了更人性化的I/O中断。
 * 被阻塞的nio通道会自动地响应中断
 * Created by laigc on 2017/3/19.
 */
class NIOBlocked implements Runnable {
    private final SocketChannel sc;

    public NIOBlocked(SocketChannel sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for read() in " + this);
            sc.read(ByteBuffer.allocate(1));
        } catch (ClosedByInterruptException e) {
            System.out.println("ClosedByInterruptException");
        } catch (AsynchronousCloseException e) {
            System.out.println("AsynchronousCloseException");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Exiting NIOBlocked.run() " + this);
    }
}

public class NIOInterruption {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa);
        Future<?> future = threadPool.submit(new NIOBlocked(sc1));
        threadPool.execute(new NIOBlocked(sc2));
        threadPool.shutdown();
        TimeUnit.SECONDS.sleep(1);
        // 中断
        future.cancel(true);
        TimeUnit.SECONDS.sleep(1);
        sc2.close();
    }
}
