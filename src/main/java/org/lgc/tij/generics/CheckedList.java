package org.lgc.tij.generics;

import org.lgc.tij.typeinfo.pets.Cat;
import org.lgc.tij.typeinfo.pets.Dog;
import org.lgc.tij.typeinfo.pets.Pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 动态类型安全
 * 因为jdk5之前没有泛型，不使用泛型时，可以用Collections来检查向容器添加对象的合法性
 * Created by lgc on 17-2-8.
 */
public class CheckedList {
    @SuppressWarnings("unchecked")
    private static void oldStyleMethod(List dogs) { // 这里的参数没有使用泛型
        dogs.add(new Cat()); // 添加Cat对象
    }

    public static void main(String[] args) {
        List<Dog> dogs1 = new ArrayList<>();
        oldStyleMethod(dogs1); // 这里调用方法可以添加Dog的List中添加Cat对象
        List<Dog> dogs2 = Collections.checkedList(new ArrayList<>(), Dog.class);
        try {
            oldStyleMethod(dogs2); // 这样创建的List不能添加Cat对象，会有异常
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Pet> pets = Collections.checkedList(new ArrayList<>(), Pet.class);
        pets.add(new Dog());
        pets.add(new Cat());
    }
}
