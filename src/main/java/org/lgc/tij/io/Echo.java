package org.lgc.tij.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 从标准输入中读取
 * Created by laigc on 2017/2/25.
 */
public class Echo {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = bufferedReader.readLine()) != null && s.length() != 0) {
            System.out.println(s);
        }
    }
}
