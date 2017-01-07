package org.lgc.tij.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型创建List并填充数据
 * Created by laigc on 2017/1/7.
 */
public class FilledListMaker<T> {
    List<T> create(T t, int n) {
        // 即使编译器无法知道有关create()中的T信息，但是它仍旧可以在编译期确保你放置到result中的对象具有T类型
        // 使其适合ArrayList<T>。因此，即使擦除在方法或类内部移除了有关实际类型的信息，编译器仍旧可以确保在方法或
        // 类中使用的类型的内部一致性
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < n; i++) {
            list.add(t);
        }
        return list;
    }

    public static void main(String[] args) {
        FilledListMaker<String> stringMaker = new FilledListMaker<>();
        List<String> list = stringMaker.create("hello", 5);
        System.out.println(list);
    }
}
