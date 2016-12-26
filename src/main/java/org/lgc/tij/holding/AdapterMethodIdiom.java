package org.lgc.tij.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * 我们希望在默认的前向迭代器的基础上，添加产生反向迭代器的能力
 * 当你有一个接口并需要另一个接口时，编写适配器就可以解决问题
 * 这里添加一个能够产生Iterable对象的方法，该对象可以用于foreach语句
 * Created by laigc on 2016/12/24.
 */
class ReversibleArrayList<T> extends ArrayList<T> {
    public ReversibleArrayList(Collection<T> c) {
        super(c);
    }

    public Iterable<T> reversed() {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    int current = size() - 1;

                    @Override
                    public boolean hasNext() {
                        return current > -1;
                    }

                    @Override
                    public T next() {
                        return get(current--);
                    }
                };
            }
        };
    }
}

public class AdapterMethodIdiom {
    public static void main(String[] args) {
        ReversibleArrayList<String> ral = new ReversibleArrayList<>(Arrays.asList(("To be or not to be").split(" ")));
        for (String s : ral) { // 得到默认的迭代器
            System.out.print(s + " ");
        }
        System.out.println();
        for (String s : ral.reversed()) { // 得到反向迭代器
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
