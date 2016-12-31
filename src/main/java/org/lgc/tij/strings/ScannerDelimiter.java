package org.lgc.tij.strings;

import java.util.Scanner;

/** Scanner定界符
 *  在默认的情况下，Scanner根据空白字符对输入进行分词，但是你可以用正则表达式指定自己所需的定界符
 * Created by laigc on 2016/12/31.
 */
public class ScannerDelimiter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("12, 23, 58, 99, 76");
        // 指定定界符
        scanner.useDelimiter("\\s*,\\s*");
        while (scanner.hasNextInt()) {
            System.out.println(scanner.nextInt());
        }
    }
}
