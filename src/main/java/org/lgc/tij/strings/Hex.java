package org.lgc.tij.strings;

import net.mindview.util.BinaryFile;

import java.io.IOException;

/**
 * 一个十六进制转储工具
 * Created by laigc on 2016/12/30.
 */
public class Hex {
    public static String format(byte[] data) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];
            if (i % 16 == 0) {
                // 行首添加十六进制顺序
                result.append(String.format("%05x: ", i));
            }
            result.append(String.format("%02x ", b));
            if ((i + 1) % 16 == 0) {
                // 行尾添加换行符
                result.append("\n");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(format(BinaryFile.read("pom.xml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
