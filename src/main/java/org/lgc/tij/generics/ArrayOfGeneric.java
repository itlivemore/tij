package org.lgc.tij.generics;

/**
 * 泛型数组
 * Created by laigc on 2017/1/7.
 */
class Generic<T> {
}

public class ArrayOfGeneric {
    static final int SIZE = 100;
    static Generic<Integer>[] gia;

    public static void main(String[] args) {
        // 会有ClassCastExceptoion
//        gia = (Generic<Integer>[]) new Object[SIZE];
        gia = new Generic[SIZE];
        System.out.println(gia.getClass().getName());
        gia[0] = new Generic<Integer>();
//        gia[1] = new Object();
//        gia[2] = new Generic<Double>();
    }
}
