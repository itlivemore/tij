package org.lgc.tij.concurrency;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 任务间使用管道进行输入/输出
 * Created by laigc on 2017/3/25.
 */
class Sender implements Runnable {
    private Random random = new Random(47);
    private PipedWriter out = new PipedWriter();

    public PipedWriter getPipedWriter() {
        return out;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (char c = 'A'; c <= 'z'; c++) {
                    out.write(c);
                    System.out.println("Write: " + c);
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            }
        } catch (IOException e) {
            System.out.println(e + " Sender write exception");
        } catch (InterruptedException e) {
            System.out.println(e + " Sender sleep interrupted");
        }
    }
}

class Receiver implements Runnable {
    private PipedReader in;

    public Receiver(Sender sender) throws IOException {
        this.in = new PipedReader(sender.getPipedWriter());
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Read: " + (char) in.read());
            }
        } catch (IOException e) {
            System.out.println(e + " Receiver read exception");
        }
    }
}

public class PipedIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(sender);
        threadPool.execute(receiver);
        TimeUnit.SECONDS.sleep(5);
        threadPool.shutdownNow();
    }
}
