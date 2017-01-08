package org.lgc.tij.generics;

import java.util.Arrays;
import java.util.List;

/**
 * ?通配符
 * Created by laigc on 2017/1/8.
 */
public class CompilerIntelligence {
    public static void main(String[] args) {
        List<? extends Fruit> fruits = Arrays.asList(new Apple());
        Apple apple = (Apple) fruits.get(0);
        fruits.contains(new Apple());// 参数是Object
        fruits.indexOf(new Apple());// 参数是Object
    }
}
