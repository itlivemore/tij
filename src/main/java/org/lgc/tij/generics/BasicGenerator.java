package org.lgc.tij.generics;

import net.mindview.util.Generator;

/**
 * 一个通用的Generator
 * 这个类提供了一个基本实现，用以生成某个类的对象。这个类必须具备两个特点：
 * （1）它必须声明为public
 * （2）它必须具备默认的构造器
 * 要创建这个的BasicGenerator，只需要调用create()方法
 * Created by laigc on 2017/1/2.
 */
public class BasicGenerator<T> implements Generator<T> {
    private Class<T> type;

    public BasicGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next() {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<T>(type);
    }
}
