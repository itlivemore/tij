package org.lgc.tij.generics;

/**
 * 擦除丢失了在泛型代码中执行某些操作的能力，任何在运行时需要知道确切类型信息的操作都将无法工作
 * Created by laigc on 2017/1/7.
 */
public class Erased<T> {
    private final int SIZE = 100;

    public static void f(Object arg) {
//        if (arg instanceof T) {}
//        T var = new T();
//        T[] array = new T[SIZE];
//        T[] array2 = new Object[SIZE];
    }
}
