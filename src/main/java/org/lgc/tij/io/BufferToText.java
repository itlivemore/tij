package org.lgc.tij.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 将ByteBuffer转成字符串
 * Created by laigc on 2017/2/26.
 */
public class BufferToText {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws Exception {
        FileChannel fileChannel = new FileOutputStream("data2.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("Some Text".getBytes()));
        fileChannel.close();

        fileChannel = new FileInputStream("data2.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fileChannel.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());// 乱码

        // 使用系统默认编码解码
        buffer.rewind();// 返回到数据开始部分
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decoded using " + encoding + " : " + Charset.forName(encoding).decode(buffer));

        // 存储时就设置编码方式
        fileChannel = new FileOutputStream("data2.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("Some Text".getBytes("UTF-16BE")));
        fileChannel.close();
        fileChannel = new FileInputStream("data2.txt").getChannel();
        buffer.clear();
        fileChannel.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());

        // 通过CharBuffer来写入
        fileChannel = new FileOutputStream("data2.txt").getChannel();
        buffer = ByteBuffer.allocate(24);
        buffer.asCharBuffer().put("Some text");
        fileChannel.write(buffer);
        fileChannel.close();
        // 读取
        fileChannel = new FileInputStream("data2.txt").getChannel();
        buffer.clear();
        fileChannel.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
    }
}
