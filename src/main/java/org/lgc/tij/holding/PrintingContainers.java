package org.lgc.tij.holding;

import java.util.*;

/**
 * 容器的打印
 * List,以特定的顺序保存一组元素
 * Set,元素不能重复
 * HashSet保存元素是无序的，如果存储顺序很重要，可以使用TreeSet，它按照比较结果的升序保存对象，
 *  或者使用LinkedHashSet，它按照被添加的顺序保存对象
 * Created by lgc on 16-12-13.
 */
public class PrintingContainers {
    static Collection fill(Collection<String> collection) {
        collection.add("rat");
        collection.add("cat");
        collection.add("dog");
        collection.add("dog");
        return  collection;
    }

    static Map fill(Map<String, String> map) {
        map.put("rat", "Fuzzy");
        map.put("cat", "Rags");
        map.put("dog", "Bosco");
        map.put("dog", "Spog");
        return map;
    }

    public static void main(String[] args) {
        System.out.println(fill(new ArrayList<String>()));
        System.out.println(fill(new LinkedList<String>()));
        System.out.println(fill(new HashSet<String>()));
        System.out.println(fill(new TreeSet<String>()));
        System.out.println(fill(new LinkedHashSet<String>()));
        System.out.println(fill(new HashMap<String, String>()));
        System.out.println(fill(new TreeMap<String, String>()));
        System.out.println(fill(new LinkedHashMap<String, String>()));
    }
}
