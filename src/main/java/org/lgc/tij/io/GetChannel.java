package org.lgc.tij.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 获取通道
 * Created by laigc on 2017/2/26.
 */
public class GetChannel {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        try {
            // 写入文件
            FileChannel fileChannel = new FileOutputStream("data.txt").getChannel();
            fileChannel.write(ByteBuffer.wrap("Some text ".getBytes()));
            fileChannel.close();

            // 在文件末尾追加
            fileChannel = new RandomAccessFile("data.txt", "rw").getChannel();
            fileChannel.position(fileChannel.size());//移动到末尾
            fileChannel.write(ByteBuffer.wrap("Some more ".getBytes()));
            fileChannel.close();

            // 读文件
            fileChannel = new FileInputStream("data.txt").getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(BSIZE);
            // 一旦调用read()来千知FileChannel向ByteBuffer存储字节，就必须调用缓冲器上的flip()，让它做好让别人读取字节的准备
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
