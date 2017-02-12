package org.lgc.tij.arrays;

import java.util.Arrays;

/**
 * 本例总结了初始化数组的各种方式，以及如何对指向数组的引用赋值，使之指向另一个数组对象.
 * 本例也说明，对象数组和基本类型数组在使用上几乎是相同的，唯一的区别就是对象数组保存的是引用，
 * 基本类型数组直接保存基本类型的值
 * 初始化数组的方式：
 * int[] f = new int[5];
 * int[] h = {11, 47, 93};
 * int[] e = new int[]{1, 2};
 * Created by laigc on 2017/2/12.
 */
public class ArrayOptions {
    public static void main(String[] args) {
        BerylliumSphere[] a;
        BerylliumSphere[] b = new BerylliumSphere[5];
        System.out.println("b: " + Arrays.toString(b));
        BerylliumSphere[] c = new BerylliumSphere[4];
        for (int i = 0; i < c.length; i++) {
            if (c[i] == null) {
                c[i] = new BerylliumSphere();
            }
        }
        BerylliumSphere[] d = {new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()};
        a = new BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere()};
        System.out.println("a.length = " + a.length);
        System.out.println("b.length = " + b.length);
        System.out.println("c.length = " + c.length);
        System.out.println("d.length = " + d.length);

        int[] e;
        int[] f = new int[5];
        System.out.println("f: " + Arrays.toString(f));// f被自动初始化为0
        int[] g = new int[4];
        for (int i = 0; i < g.length; i++) {
            g[i] = i * i;
        }
        int[] h = {11, 47, 93};
//        System.out.println(e.length); //error,e尚未初始化
        System.out.println("f.length = " + f.length);
        System.out.println("g.length = " + g.length);
        System.out.println("h.length = " + h.length);
        e = h;
        System.out.println("e.length = " + e.length);
        e = new int[]{1, 2};
        System.out.println("e.length = " + e.length);
    }
}
