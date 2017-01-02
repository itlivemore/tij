package org.lgc.tij.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 可变参数与泛型方法
 * Created by laigc on 2017/1/2.
 */
public class GenericVarargs {
    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<>();
        for (T arg : args) {
            result.add(arg);
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list = makeList("A");
        System.out.println(list);
        list = makeList("A", "B", "C");
        System.out.println(list);
        list = makeList("ABCDEFGHIJLMNOPQRSTUVWXYZ".split(""));
        System.out.println(list);
    }
}
