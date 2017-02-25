package org.lgc.tij.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 读取二进制文件
 * Created by laigc on 2017/2/25.
 */
public class BinaryFile {
    public static byte[] read(File bFile) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(bFile));
        try {
            byte[] data = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(data);
            return data;
        } finally {
            bufferedInputStream.close();
        }
    }

    public static byte[] read(String bFile) throws IOException {
        return read(new File(bFile).getAbsoluteFile());
    }

    public static void main(String[] args) {
        String readFile = TextFile.class.getClassLoader().getResource("io/testFileTest.txt").getPath();
        try {
            System.out.println(Arrays.toString(read(readFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
