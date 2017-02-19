package org.lgc.tij.containers;

import java.util.*;

/**
 * 生成只读容器
 * Created by laigc on 2017/2/19.
 */
public class ReadOnly {
    static Collection<String> data = new ArrayList<>(Countries.names(6));

    public static void main(String[] args) {
        Collection<String> collection = Collections.unmodifiableCollection(new ArrayList<String>(data));
        System.out.println(collection);
//        collection.add("one");// 不能修改

        List<String> list = Collections.unmodifiableList(new ArrayList<>(data));
        ListIterator<String> iterator = list.listIterator();
        System.out.println(iterator.next());
//        iterator.add("one"); // 不能修改

        Set<String> s = Collections.unmodifiableSet(new HashSet<>(data));
        System.out.println(s);
//        s.add("one");

        SortedSet<String> sortedSet = Collections.unmodifiableSortedSet(new TreeSet<>(data));
        System.out.println(sortedSet);

        Map<String, String> map = Collections.unmodifiableMap(new HashMap<>(Countries.capitals()));
        System.out.println(map);
//        map.put("aa", "bb");

        SortedMap<String, String> map1 = Collections.unmodifiableSortedMap(new TreeMap<>(Countries.capitals()));
    }
}
