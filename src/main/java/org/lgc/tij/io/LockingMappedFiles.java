package org.lgc.tij.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 对映射文件的部分加锁
 * Created by laigc on 2017/2/26.
 */
public class LockingMappedFiles {
    static final int LENGTH = 0x8FFFFFF; // 128MB
    static FileChannel fileChannel;

    public static void main(String[] args) throws Exception {
        fileChannel = new RandomAccessFile("test.dat", "rw").getChannel();
        MappedByteBuffer out = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            out.put((byte) 'X');
        }
        new LockAndModify(out, 0, 0 + LENGTH / 3);
        new LockAndModify(out, LENGTH / 2, LENGTH / 2 + LENGTH / 4);
    }

    private static class LockAndModify extends Thread {
        private ByteBuffer byteBuffer;
        private int start, end;

        public LockAndModify(ByteBuffer buffer, int start, int end) {
            this.start = start;
            this.end = end;
            buffer.limit(end);
            buffer.position(start);
            this.byteBuffer = buffer.slice();
            start();
        }

        @Override
        public void run() {
            try {
                FileLock lock = fileChannel.lock(start, end, false);
                System.out.println("Locked: " + start + " to " + end);
                while (byteBuffer.position() < byteBuffer.limit() - 1) {
                    byteBuffer.put((byte) (byteBuffer.get() + 1));
                }
                lock.release();
                System.out.println("Release: " + start + " to " + end);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
