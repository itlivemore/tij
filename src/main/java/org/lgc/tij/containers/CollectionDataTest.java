package org.lgc.tij.containers;

import net.mindview.util.CollectionData;
import net.mindview.util.Generator;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 测试CollectionData,CollectionData可以产生容器，可以将这个容器传递给任何Collection的构造器
 * Created by laigc on 2017/2/12.
 */
class Government implements Generator<String> {
    String[] foundation = ("strange woman lying on ponds distributing " +
            "swords is no basic for a system of goverment").split(" ");
    private int index;

    @Override
    public String next() {
        return foundation[index++];
    }
}

public class CollectionDataTest {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>(new CollectionData<>(new Government(), 15));
        set.addAll(CollectionData.list(new Government(), 15));
        System.out.println(set);
    }
}
