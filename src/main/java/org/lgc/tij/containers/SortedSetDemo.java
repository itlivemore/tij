package org.lgc.tij.containers;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * SortedSet示例 * Created by laigc on 2017/2/18.
 */
public class SortedSetDemo {
    public static void main(String[] args) {
        SortedSet<String> sortedSet = new TreeSet<>();
        Collections.addAll(sortedSet, "one two three four five six seven eight".split(" "));
        System.out.println(sortedSet);

        String low = sortedSet.first(); // 返回第一个元素
        String high = sortedSet.last(); // 返回最后一个元素
        System.out.println(low);
        System.out.println(high);

        Iterator<String> iterator = sortedSet.iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) {
                low = iterator.next();
            }
            if (i == 6) {
                high = iterator.next();
            } else {
                iterator.next();
            }
        }
        System.out.println(low);
        System.out.println(high);

        System.out.println(sortedSet.subSet(low, high)); // 生成子集，范围从第一个参数(包含)到第二个参数(不包含)
        System.out.println(sortedSet.headSet(high)); // 生成子集，由小于参数的元素组成
        System.out.println(sortedSet.tailSet(low)); // 生成子集，由大于或等于参数的元素组成
    }
}
