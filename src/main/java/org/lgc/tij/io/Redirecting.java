package org.lgc.tij.io;

import java.io.*;

/**
 * 标准I/O重定向
 * 这个程序将标准输入附接到文件上，并将标准输出和标准错误重定向到另一个文件
 * Created by laigc on 2017/2/25.
 */
public class Redirecting {
    public static void main(String[] args) throws Exception {
        String path = Redirecting.class.getClassLoader().getResource("io/testFileTest.txt").getPath();

        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));

        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.out")));

        System.setIn(in);
        System.setOut(out);
        System.setErr(out);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }

        out.close();
        System.setOut(console);
    }
}
