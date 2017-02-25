package org.lgc.tij.io;

import java.io.PrintWriter;

/** 将System.out转换成PrintWriter
 * Created by laigc on 2017/2/25.
 */
public class ChangeSystemOut {
    public static void main(String[] args) {
        PrintWriter printWriter = new PrintWriter(System.out, true);
        printWriter.println("hello, world!");
    }
}
