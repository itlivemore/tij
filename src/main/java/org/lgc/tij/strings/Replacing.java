package org.lgc.tij.strings;

/**
 * String的替换方法
 * Created by laigc on 2016/12/31.
 */
public class Replacing {
    private static String s = Splitting.knights;

    public static void main(String[] args) {
        // 匹配以f开头，后面跟一个或多个字母。这里只替换掉第一个匹配的部分
        System.out.println(s.replaceFirst("f\\w+", "located"));
        System.out.println(s.replaceAll("f\\w+", "located")); //替换所有匹配到的部分
        System.out.println(s.replaceAll("shrubbery|tree|herring", "banana"));// 匹配三个单词中的任意一个，并且替换所有匹配到的
    }
}
