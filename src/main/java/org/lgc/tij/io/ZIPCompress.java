package org.lgc.tij.io;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * 用Zip进行多文件保存
 * Created by laigc on 2017/2/26.
 */
public class ZIPCompress {
    public static void main(String[] args) throws IOException {
        args = new String[]{"data.txt", "data2.txt"};

        FileOutputStream fileOutputStream = new FileOutputStream("test.zip");
        // 一共有两种Checksum类型，Adler32(它快一些)和CRC32(慢)
        CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new Adler32());
        ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream);
        zipOutputStream.setComment("A test of Java Zipping");

        for (String arg : args) {
            System.out.println("Writing file " + arg);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(arg));
            zipOutputStream.putNextEntry(new ZipEntry(arg));
            int c;
            while ((c = bufferedReader.read()) != -1) {
                bufferedOutputStream.write(c);
            }
            bufferedReader.close();
            bufferedOutputStream.flush();
        }
        bufferedOutputStream.close();
        System.out.println(checkedOutputStream.getChecksum().getValue());

        // 提取文件
        System.out.println("Reading file");
        FileInputStream fileInputStream = new FileInputStream("test.zip");
        CheckedInputStream checkedInputStream = new CheckedInputStream(fileInputStream, new Adler32());
        ZipInputStream zipInputStream = new ZipInputStream(checkedInputStream);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(zipInputStream);
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            System.out.println("Reading file " + zipEntry);
            int x;
            while ((x = bufferedInputStream.read()) != -1) {
                System.out.write(x);
            }
        }

        if (args.length == 1) {
            System.out.println("Checksum: " + checkedInputStream.getChecksum().getValue());
        }
        bufferedInputStream.close();
        // 另一种读取方式
        ZipFile zipFile = new ZipFile("test.zip");
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry1 = entries.nextElement();
            System.out.println("File: " + zipEntry1);
        }
    }
}
