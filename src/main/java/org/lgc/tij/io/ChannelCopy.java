package org.lgc.tij.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通过通道复制文件
 * Created by laigc on 2017/2/26.
 */
public class ChannelCopy {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws Exception {
        String file1 = "data.txt";
        String file2 = "data2.txt";

        FileChannel in = new FileInputStream(file1).getChannel();
        FileChannel out = new FileOutputStream(file2).getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        while (in.read(buffer) != -1) {
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }
    }
}
