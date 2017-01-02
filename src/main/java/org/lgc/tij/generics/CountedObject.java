package org.lgc.tij.generics;

/**
 * 计数类
 * Created by laigc on 2017/1/2.
 */
public class CountedObject {
    private static long counter = 0;
    private final long id = counter++;

    public long id() {
        return id;
    }

    @Override
    public String toString() {
        return "CountedObject{" +
                "id=" + id +
                '}';
    }
}
