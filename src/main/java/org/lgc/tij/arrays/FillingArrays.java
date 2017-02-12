package org.lgc.tij.arrays;

import java.util.Arrays;

/**
 * 填充数组
 * Arrays.fill()方法
 * Created by laigc on 2017/2/12.
 */
public class FillingArrays {
    public static void main(String[] args) {
        int size = 6;
        int[] ints = new int[size];
        Arrays.fill(ints, 3);
        System.out.println(Arrays.toString(ints));

        String[] strings = new String[size];
        Arrays.fill(strings, "Hello");
        System.out.println(Arrays.toString(strings));
        Arrays.fill(strings, 3, 5, "world"); // 可以选择填充位置
        System.out.println(Arrays.toString(strings));

    }
}
