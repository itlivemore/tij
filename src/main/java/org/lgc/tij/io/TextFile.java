package org.lgc.tij.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * 工具类，简化对文件的读写操作
 * Created by laigc on 2017/2/25.
 */
public class TextFile extends ArrayList<String> {

    /**
     * 将文件读取成String
     *
     * @param filename 文件名
     * @return
     */
    public static String read(String filename) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename).getAbsoluteFile()));
            try {
                String s;
                while ((s = bufferedReader.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                bufferedReader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    // 将text写入到文件filename
    public static void write(String filename, String text) {
        try {
            PrintWriter printWriter = new PrintWriter(new File(filename).getAbsoluteFile());
            try {
                printWriter.print(text);
            } finally {
                printWriter.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public TextFile(String filename, String splitter) {
        // 读取filename为String，然后用splitter分割
        super(Arrays.asList(read(filename).split(splitter)));
        if (get(0).equals("")) {
            remove(0);
        }
    }

    public TextFile(String filename) {
        this(filename, "\n");
    }

    public void write(String filename) {
        try {
            PrintWriter printWriter = new PrintWriter(new File(filename).getAbsoluteFile());
            try {
                for (String item : this) {
                    printWriter.println(item);
                }
            } finally {
                printWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String readFile = TextFile.class.getClassLoader().getResource("io/testFileTest.txt").getPath();
        write("test.txt", readFile);

        TextFile text = new TextFile("test.txt");
        text.write("test2.txt");

        // 提取字符串
        TreeSet<String> words = new TreeSet<>(new TextFile(readFile, "\\W+"));
        System.out.println(words.headSet("a"));
    }
}
