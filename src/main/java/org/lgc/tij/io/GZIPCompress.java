package org.lgc.tij.io;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/** 用GZIP进行简单压缩
 * Created by laigc on 2017/2/26.
 */
public class GZIPCompress {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("test.dat"));

        FileOutputStream fileOutputStream = new FileOutputStream("test.gz");
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);
        BufferedOutputStream out = new BufferedOutputStream(gzipOutputStream);
        System.out.println("Writing file");
        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }
        in.close();
        out.close();

        System.out.println("Reading file");
        BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("test.gz"))));
        String s;
        while ((s = in2.readLine()) != null) {
            System.out.println(s);
        }

    }
}
