package org.lgc.tij.containers;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * SortedMap(TreeMap是其现阶段的唯一实现)可以确保键处于排序状态
 * Created by laigc on 2017/2/18.
 */
public class SortedMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> sortedMap = new TreeMap<>(new CountingMapData(10));
        System.out.println(sortedMap);

        Integer low = sortedMap.firstKey();
        Integer high = sortedMap.lastKey();
        System.out.println(low);
        System.out.println(high);

        Iterator<Integer> iterator = sortedMap.keySet().iterator();
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
        System.out.println(sortedMap.subMap(low, high)); // 生成子集，范围从第一个参数(包含)到第二个参数(不包含)
        System.out.println(sortedMap.headMap(high));// 生成子集，由小于参数的元素组成
        System.out.println(sortedMap.tailMap(low)); // 生成子集，由大于或等于参数的元素组成

    }
}
