package org.lgc.tij.arrays;

import java.util.Arrays;

/**
 * 数组的比较
 * Arrays.equals()方法，数组相等的条件是元素个数相等，并且相应位置的元素也相等（对象的equals()方法）
 * Created by laigc on 2017/2/12.
 */
public class ComparingArrays {
    public static void main(String[] args) {
        int[] a1 = new int[10];
        int[] a2 = new int[10];
        Arrays.fill(a1, 47);
        Arrays.fill(a2, 47);
        System.out.println(Arrays.equals(a1, a2));
        a2[3] = 2;
        System.out.println(Arrays.equals(a1, a2));

        String[] s1 = new String[3];
        Arrays.fill(s1, "Hi");
        String[] s2 = {new String("Hi"), new String("Hi"), new String("Hi"),};
        System.out.println(Arrays.equals(s1, s2)); // true,可以看出是数组中的元素是用equals()方法比较的
    }
}
