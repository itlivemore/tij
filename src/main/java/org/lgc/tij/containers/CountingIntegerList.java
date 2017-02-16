package org.lgc.tij.containers;

import java.util.AbstractList;

/**
 * 本例中的类是一个List，它可以具有任意尺寸，并且用Integer数据进行了预初始化
 * Created by lgc on 17-2-15.
 */
public class CountingIntegerList extends AbstractList<Integer> {
    private int size;

    public CountingIntegerList(int size) {
        this.size = size < 0 ? 0 : size;
    }

    @Override
    public Integer get(int index) {
        return Integer.valueOf(index);
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        System.out.println(new CountingIntegerList(30));
    }
}
