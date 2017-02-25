package org.lgc.tij.io;

import java.io.*;

/**
 * 使用DataInputStream读取格式化数据，DataInputStream是面向字节的I/O类
 * Created by laigc on 2017/2/25.
 */
public class FormattedMemoryInput {
    public static void main(String[] args) {
        try {
            File file = new File(new File("").getAbsolutePath() + "/pom.xml");
            String s = BufferedInputFile.read(file.getAbsolutePath());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(s.getBytes());
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            while (true) {
                //DataInputStream的readByte()一次一个字节地读取字符，任何字节的值都是合法的结果，因此返回值不能用来检测
                // 输入是否结束，可以使用available()方法来查看还有多少可供存取的字符
                System.out.println((char) dataInputStream.readByte());
            }
        } catch (EOFException e) {
            System.out.println("End of stream");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file = new File(new File("").getAbsolutePath() + "/pom.xml");
            String s = BufferedInputFile.read(file.getAbsolutePath());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(s.getBytes());
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            while (dataInputStream.available() != 0) {
                System.out.println((char) dataInputStream.readByte());
            }
        } catch (EOFException e) {
            System.out.println("End of stream");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
