package org.lgc.tij.generics;

import java.util.Arrays;
import java.util.List;

/**
 * 协变与通配符
 * Created by laigc on 2017/1/8.
 */
public class GenericReading {
    static <T> T readExact(List<T> list) {
        return list.get(0);
    }

    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruits = Arrays.asList(new Fruit());

    static void f1() {
        Apple apple = readExact(apples);
        Fruit fruit = readExact(fruits);
        fruit = readExact(apples);
    }

    static class Reader<T> {
        T readExact(List<T> list) {
            return list.get(0);
        }
    }

    static void f2() {
        Reader<Fruit> fruitReader = new Reader<>();
        Fruit fruit = fruitReader.readExact(fruits);
//        fruitReader.readExact(apples);// fruitReader.readExact()接受List<Fruit>，所以这里不能调用
    }

    static class ConveriantReaer<T> {
        T readConvariant(List<? extends T> list) {
            return list.get(0);
        }
    }

    static void f3() {
        ConveriantReaer<Fruit> fruitConveriantReaer = new ConveriantReaer<>();
        Fruit fruit = fruitConveriantReaer.readConvariant(fruits);
        Fruit fruit1 = fruitConveriantReaer.readConvariant(apples);
    }

    public static void main(String[] args) {
        f1();
        f2();
        f3();
    }
}
