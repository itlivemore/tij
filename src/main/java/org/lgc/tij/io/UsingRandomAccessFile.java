package org.lgc.tij.io;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 读写随机访问文件
 * Created by laigc on 2017/2/25.
 */
public class UsingRandomAccessFile {
    static String path = UsingRandomAccessFile.class.getClassLoader().getResource("io/rtest.dat").getPath();

    static void display() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
        for (int i = 0; i < 7; i++) {
            System.out.println("Value " + i + ": " + randomAccessFile.readDouble());
        }
        System.out.println(randomAccessFile.readUTF());
        randomAccessFile.close();
    }

    public static void main(String[] args) {
        try {
            RandomAccessFile rf = new RandomAccessFile(path, "rw");
            for (int i = 0; i < 7; i++) {
                rf.writeDouble(i * 1.414);
            }
            rf.writeUTF("The end of file");
            rf.close();

            display();

            rf = new RandomAccessFile(path, "rw");
            // 因为double长度是8字节长，所以这里跳到了第5个double，修改了第5个double的值
            rf.seek(5 * 8);
            rf.writeDouble(47.0001);
            rf.close();
            display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
