package org.lgc.tij.holding;

import java.util.Iterator;

/**
 * Iterable接口包含了一个能够产生Iterator的iterator()方法，并且Iterable接口被foreach用来在序列中移动。
 * 因此如果你创建了任何实现Iterable的类，都可以用于foreach语句中
 * Created by laigc on 2016/12/24.
 */
public class IterableClass implements Iterable<String> {
    protected String[] words = ("And that is how we know the Earth to be banana-shaped.").split(" ");

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() {
                return words[index++];
            }
        };
    }

    public static void main(String[] args) {
        for (String s : new IterableClass()
               ) {
            System.out.print(s + " ");
        }
    }
}
