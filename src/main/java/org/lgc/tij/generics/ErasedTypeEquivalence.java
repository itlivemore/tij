package org.lgc.tij.generics;

import java.util.ArrayList;

/**
 * 虽然可以声明ArrayList.class，但是不能声明ArrayList<Integer>.class
 * Created by laigc on 2017/1/6.
 */
public class ErasedTypeEquivalence {
    public static void main(String[] args) {
        Class<? extends ArrayList> c1 = new ArrayList<Integer>().getClass();
        Class<? extends ArrayList> c2 = new ArrayList<String>().getClass();
        System.out.println(c1 == c2); // true
    }
}
