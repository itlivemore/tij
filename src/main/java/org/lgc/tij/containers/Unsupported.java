package org.lgc.tij.containers;

import java.util.*;

/**
 * 未获支持的操作
 * Arrays.asList()会生成一个List，它基于一个固定大小的数组，仅支持那些不会改变数组大小的操作,
 * 任何会引起对底层数据结构的尺寸进行修改的方法都会产生一个UnsupportedOperationException异常
 * 注意最后的set()方法，Arrays.AsList()支持，Collections.unmodifiableList()不支持
 * Created by laigc on 2017/2/18.
 */
public class Unsupported {
    static void test(String msg, List<String> list) {
        System.out.println("----------" + msg + "----------");
        Collection<String> collection = list;
        Collection<String> subList = list.subList(1, 8);
        Collection<String> collection2 = new ArrayList<>(subList);

        try {
            collection.retainAll(collection2);
        } catch (Exception e) {
            System.out.println("retainAll: " + e);
        }

        try {
            collection.removeAll(collection2);
        } catch (Exception e) {
            System.out.println("removeAll: " + e);
        }

        try {
            collection.clear();
        } catch (Exception e) {
            System.out.println("clear: " + e);
        }

        try {
            collection.add("X");
        } catch (Exception e) {
            System.out.println("add: " + e);
        }

        try {
            collection.addAll(collection2);
        } catch (Exception e) {
            System.out.println("addAll: " + e);
        }

        try {
            collection.remove("C");
        } catch (Exception e) {
            System.out.println("remove: " + e);
        }

        try {
            list.set(0, "X");
        } catch (Exception e) {
            System.out.println("List.set: " + e);
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("A B C D E F G H I J K L ".split(" "));
        test("Modifiable Copy", new ArrayList<>(list));
        test("Arrays.asList", list);
        test("unmodifiableList()", Collections.unmodifiableList(new ArrayList<>(list)));
    }
}
