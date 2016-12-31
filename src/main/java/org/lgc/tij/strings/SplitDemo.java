package org.lgc.tij.strings;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Pattern的split()方法
 * split()方法将输入的字符串断开成字符串对象数组
 * Created by laigc on 2016/12/31.
 */
public class SplitDemo {
    public static void main(String[] args) {
        String input = "This!!unusual use!!of exclamation!!points";
        Pattern pattern = Pattern.compile("!!");
        String[] split = pattern.split(input);
        System.out.println(Arrays.toString(split));

        split = pattern.split(input, 3); // 可以限制将输入的字符串分割的数量
        System.out.println(Arrays.toString(split));
    }
}
