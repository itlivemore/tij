package org.lgc.tij.strings;

import java.util.Arrays;

/**
 * String的split()方法
 * Created by laigc on 2016/12/31.
 */
public class Splitting {
    public static String knights = "Then, When you have found the shrubbery, you must " +
            "cut down the mightiest tree in the forest... with... a herring!";

    public static void split(String regex) {
        System.out.println(Arrays.toString(knights.split(regex)));
    }

    public static void main(String[] args) {
        split(" "); // 按空格分隔
        split("\\W+"); // \\W表示非单词字符。从结果看：分隔时把标点字符都删除了
        split("n\\W+"); // n\\W+表示字母n后面跟着一个或多个非单词字符
    }
}
