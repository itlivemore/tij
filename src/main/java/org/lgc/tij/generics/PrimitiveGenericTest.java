package org.lgc.tij.generics;

import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

/**
 * 自动包装机制不能用于数组
 * Created by laigc on 2017/1/8.
 */
class FArray {
    public static <T> T[] fill(T[] a, Generator<T> generator) {
        for (int i = 0; i < a.length; i++) {
            a[i] = generator.next();
        }
        return a;
    }
}

public class PrimitiveGenericTest {
    public static void main(String[] args) {
        String[] strings = FArray.fill(new String[7], new RandomGenerator.String(10));
        for (String string : strings) {
            System.out.println(string);
        }

        Integer[] integers = FArray.fill(new Integer[7], new RandomGenerator.Integer());
        for (Integer integer : integers) {
            System.out.println(integer);
        }

//        FArray.fill(new int[7], new RandomGenerator.Integer());
    }
}
