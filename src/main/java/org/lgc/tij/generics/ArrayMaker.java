package org.lgc.tij.generics;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 泛型有了擦除，可以表示没有任何意义的事物
 * Created by laigc on 2017/1/6.
 */
public class ArrayMaker<T> {
    // 即使kind被存储为Class<T>，擦除也意味着它实际将被存储为Class，没有任何参数
    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }

    T[] create(int size) {
        // 在创建数组时，Array.newInstance()实际上并未拥有kind所蕴含的类型信息，因此这不会产生具体的结果，所以必须转型
        // 注意：对于在泛型中创建数组，使用Array.newInstance()是推荐的方式
        return (T[]) Array.newInstance(kind, size);
    }

    public static void main(String[] args) {
        ArrayMaker<String> stringMaker = new ArrayMaker<>(String.class);
        String[] stringArray = stringMaker.create(9);
        System.out.println(Arrays.toString(stringArray));

    }
}
