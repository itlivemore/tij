package org.lgc.tij.holding;

import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

/**
 * Created by lgc on 16-12-19.
 */
public class SetOfInteger {
    public static void main(String[] args) {
        Random random = new Random(47);

        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int i = 0; i < 10000; i++) {
            hashSet.add(random.nextInt(30));
        }
        System.out.println(hashSet); // HashSet是无序的

        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        for (int i = 0; i < 10000; i++) {
            treeSet.add(random.nextInt(30));
        }
        System.out.println(treeSet); // TreeSet是有序的
    }
}
