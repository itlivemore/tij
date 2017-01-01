package org.lgc.tij.typeinfo;

/**
 * Class泛型限定某类型
 * Created by laigc on 2017/1/1.
 */
public class BoundedClassReferences {
    public static void main(String[] args) {
        Class<? extends Number> bounded = int.class;
        bounded = double.class;
        bounded = Number.class;
    }
}
