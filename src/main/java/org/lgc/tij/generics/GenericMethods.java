package org.lgc.tij.generics;

/**
 * 泛型方法
 * Created by laigc on 2017/1/2.
 */
public class GenericMethods {
    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f("");
        gm.f(1); //基本类型会自动打包
        gm.f(1.0);
        gm.f(1.0f);
        gm.f('c');
        gm.f(gm);
    }
}
