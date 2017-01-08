package org.lgc.tij.generics;

/**
 * ?通配符
 * Created by laigc on 2017/1/8.
 */
public class Holder<T> {
    private T value;

    public Holder() {
    }

    public Holder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        return value.equals(o);
    }

    public static void main(String[] args) {
        Holder<Apple> appleHolder = new Holder<>(new Apple());
        Apple apple = appleHolder.getValue();
        appleHolder.setValue(apple);
//        Holder<Fruit> fruitHolder = appleHolder;
        Holder<? extends Fruit> fruitHolder = appleHolder;
        Fruit fruit = fruitHolder.getValue();
        apple = (Apple) fruitHolder.getValue();
        try {
            Orange orange = (Orange) fruitHolder.getValue();
        } catch (Exception e) {
            System.out.println(e);
        }
//        fruitHolder.setValue(new Apple());
//        fruitHolder.setValue(new Orange());
        System.out.println(fruit.equals(apple));
    }

}
