package org.lgc.tij.generics;

/** 创建泛型数组
 * Created by laigc on 2017/1/7.
 */
public class GenericArray<T> {
    private T[] array;
    public GenericArray(int size) {
        array = (T[]) new Object[size];
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
        GenericArray<Integer> integerGenericArray = new GenericArray<>(10);
        // ClassCastException，因为实际创建的是Object数组
//        Integer[] rep = integerGenericArray.rep();
        Object[] objects = integerGenericArray.rep();
    }
}
