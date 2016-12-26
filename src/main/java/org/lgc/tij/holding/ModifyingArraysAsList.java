package org.lgc.tij.holding;

import java.util.*;

/**
 * Arrays.asList()原数组被打乱问题
 * Created by laigc on 2016/12/24.
 */
public class ModifyingArraysAsList {
    public static void main(String[] args) {
        Random random = new Random(47);
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> list1 = new ArrayList<>(Arrays.asList(array));
        System.out.println("Before shuffling: " + list1);
        Collections.shuffle(list1, random);
        System.out.println("After shuffling: " + list1);
        // 可以看到将Arrays.asList()的结果用来构造List，打乱了List，数组并不会被打乱
        System.out.println("array: " + Arrays.toString(array));

        List<Integer> list2 = Arrays.asList(array);
        System.out.println("Before shuffling: " + list2);
        Collections.shuffle(list2, random);
        System.out.println("Aftrer shuffling: " + list2);
        // 下面的结果数组被打乱了，因为直接使用Arrays.asList()方法返回的List
        System.out.println("array: " + Arrays.toString(array));
    }
}
