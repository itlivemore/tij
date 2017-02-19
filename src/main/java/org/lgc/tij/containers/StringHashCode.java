package org.lgc.tij.containers;

/**
 * String的HashCode
 * 两个不同的String对象，相同的字符串内容，使用hashCode()生成相同的结果
 * 对于String而言，hashCode()是基于String的内容的
 * Created by laigc on 2017/2/18.
 */
public class StringHashCode {
    public static void main(String[] args) {
        String[] helos = "Hello Hello".split(" ");
        System.out.println(helos[0] == helos[1]);
        System.out.println(helos[0].hashCode());
        System.out.println(helos[1].hashCode());

    }
}
