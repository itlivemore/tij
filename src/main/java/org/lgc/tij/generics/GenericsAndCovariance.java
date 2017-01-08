package org.lgc.tij.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * ?通配符
 * Created by laigc on 2017/1/8.
 */
public class GenericsAndCovariance {
    public static void main(String[] args) {
//        List<Fruit> fruits = new ArrayList<Apple>();
        List<? extends Fruit> fruits = new ArrayList<Apple>();
//        fruits.add(new Apple());
//        fruits.add(new Orange());
//        fruits.add(new Object());
        fruits.add(null);
        Fruit fruit = fruits.get(0);

    }
}
