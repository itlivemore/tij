package org.lgc.tij.containers;

import java.util.LinkedHashMap;

/**
 * LinkedHashMap
 * LinkedHashMap散列化所有的元素，但是在遍历键值对时，却又以元素的插入顺序返回键值对。
 * 此外，可以在构造器中设定LinkedHashMap，使之采用基于访问的最近最少使用(LRU)算法，于是没有被访问过的元素就会出现在队列的前面
 * Created by laigc on 2017/2/18.
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>(new CountingMapData(9));
        System.out.println(linkedHashMap);

        // 使用LRU顺序
        linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.putAll(new CountingMapData(9));
        System.out.println(linkedHashMap);
        // 访问元素
        for (int i = 0; i < 6; i++) {
            linkedHashMap.get(i);
        }
        System.out.println(linkedHashMap);// 没有被访问过的元素会放在队列前面
        linkedHashMap.get(0);
        System.out.println(linkedHashMap);
    }
}
