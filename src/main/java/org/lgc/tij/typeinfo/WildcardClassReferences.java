package org.lgc.tij.typeinfo;

/**
 * Class泛型通配符
 * 通配符?表示任何事物
 * Created by laigc on 2017/1/1.
 */
public class WildcardClassReferences {
    public static void main(String[] args) {
        Class<?> intClass = int.class;
        intClass = double.class;
    }
}
