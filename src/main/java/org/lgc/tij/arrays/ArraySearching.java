package org.lgc.tij.arrays;

import net.mindview.util.ConvertTo;
import net.mindview.util.Generated;
import net.mindview.util.RandomGenerator;

import java.util.Arrays;

/**
 * 在已经排序的数组中查找
 * Created by laigc on 2017/2/12.
 */
public class ArraySearching {
    public static void main(String[] args) {
        RandomGenerator.Integer gen = new RandomGenerator.Integer(1000);
        int[] a = ConvertTo.primitive(Generated.array(new Integer[25], gen));
        Arrays.sort(a);
        System.out.println("Sorted array: " + Arrays.toString(a));

        while (true) {
            Integer next = gen.next();
            // 如果找到了目标，Arrays.binarySearch()产生的返回值等于或大于0
            int location = Arrays.binarySearch(a, next);
            if (location >= 0) {
                System.out.println("Location of " + next + " is " + location + " ,a[" + location + "] = " + a[location]);
                break;
            }
        }
    }
}
