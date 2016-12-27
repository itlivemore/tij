package org.lgc.tij.strings;

import static net.mindview.util.Print.print;

/**
 * String对象是不可变的。
 * 查看JDK文档就会发现，String类中每一个看起来会修改String值的方法，实际上都是创建了一个全新的String对象
 * 以包含修改后的字符串内容。而最初的String对象则丝毫未动
 * Created by lgc on 16-12-27.
 */
public class Immutable {
    public static String upcase(String s) {
        return s.toUpperCase();
    }

    public static void main(String[] args) {
        String q = "howdy";
        print(q);
        String qq = upcase(q);
        print(qq);
        print(q);
    }
}
