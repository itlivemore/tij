package org.lgc.tij.generics;

import java.lang.reflect.Array;

/**
 * 传递类型来创建数组
 * Created by laigc on 2017/1/7.
 */
public class GenericArrayWithTypeToken<T> {
    private T[] array;

    public GenericArrayWithTypeToken(Class<T> type, int size) {
        array = (T[]) Array.newInstance(type, size);
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] rep() {
        return array;
    }

    public static void main(String[] args) {
        GenericArrayWithTypeToken gai = new GenericArrayWithTypeToken(Integer.class, 10);
        Object[] rep = gai.rep();// 能够正常运行
    }
}
