package org.lgc.tij.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 缓冲输入文件
 * Created by laigc on 2017/2/25.
 */
public class BufferedInputFile {
    public static String read(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = br.readLine()) != null) {
            sb.append(s + "\n");
        }
        br.close();
        return sb.toString();
    }

    public static void main(String[] args) {
        File file = new File(new File("").getAbsolutePath() + "/pom.xml");

        try {
            System.out.println(read(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
