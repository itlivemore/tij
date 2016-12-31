package org.lgc.tij.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Matcher.find()方法
 * Created by laigc on 2016/12/31.
 */
public class Finding {
    public static void main(String[] args) {
        // 按单词划分
        Matcher matcher = Pattern.compile("\\w+").matcher("Evening is full of the linnet's wings");
        while (matcher.find()) {
            System.out.print(matcher.group() + " ");
        }
        System.out.println();
        int i = 0;
        // find(i)接收一个整数作为参数，该整数表示字符串中字符的位置，并以其作为搜索的起点
        while (matcher.find(i)) {
            System.out.print(matcher.group() + " ");
            i++;
        }
    }
}
