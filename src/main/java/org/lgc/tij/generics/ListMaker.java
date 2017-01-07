package org.lgc.tij.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型创建List
 * Created by laigc on 2017/1/7.
 */
public class ListMaker<T> {
    List<T> create() {
        // 在这里new ArrayList<T>中的T在运行时被移除了——在运行时，这个类的内部没有任何<T>
        // 因此这看起来毫无意义。但是如果你遵从这种思路，并将这个表达式改new ArrayList()，编译器就会报出警告
        return new ArrayList<T>();
//        return new ArrayList();
    }

    public static void main(String[] args) {
        ListMaker<String> stringMaker = new ListMaker<>();
        List<String> list = stringMaker.create();
    }
}
