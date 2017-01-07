package org.lgc.tij.generics;

/**
 * 创建泛型数组
 * Created by laigc on 2017/1/7.
 */
public class GenericArray2<T> {
    private Object[] array;

    public GenericArray2(int size) {
        array = new Object[size];
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return (T) array[index];
    }

    public T[] rep() {
        return (T[]) array;
    }

    public static void main(String[] args) {
        GenericArray2<Integer> integerGenericArray2 = new GenericArray2<>(10);
        for (int i = 0; i < 10; i++) {
            integerGenericArray2.put(i, i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(integerGenericArray2.get(i) + " ");
        }
        System.out.println();
        try {
            // 转换异常，实际上是Object数组
            Integer[] rep = integerGenericArray2.rep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
