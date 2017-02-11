package org.lgc.tij.generics;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 一个队列
 * Created by laigc on 2017/2/11.
 */
public class SimpleQueue<T> implements Iterable<T> {
    private LinkedList<T> storage = new LinkedList<T>();

    public void add(T t) {
        storage.offer(t);
    }

    public T get() {
        return storage.poll();
    }

    @Override
    public Iterator<T> iterator() {
        return storage.iterator();
    }
}
