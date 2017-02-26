package org.lgc.tij.io;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射文件
 * Created by laigc on 2017/2/26.
 */
public class LargeMappedFiles {
    static int length = 0x8FFFFFF; // 128MB

    public static void main(String[] args) throws Exception {
        MappedByteBuffer out = new RandomAccessFile("test.dat", "rw").getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, length);// 指定映射文件的初始位置和映射区域的长度
        for (int i = 0; i < length; i++) {
            out.put((byte) 'x');
        }
        System.out.println("Finished writing");
        for (int i = length / 2; i < length / 2 + 6; i++) {
            System.out.print((char) out.get(i));
        }
    }
}
