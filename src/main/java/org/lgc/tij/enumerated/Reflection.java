package org.lgc.tij.enumerated;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * 探索enum的values()方法
 * 编译器为你创建的enum类都是继承自Enum类，Enum中没有values()方法，但是我们可以使用。
 * 因为values()是由编译器添加的static方法
 * Created by laigc on 2017/3/5.
 */
enum Explore {
    HERE, THERE
}

public class Reflection {
    public static Set<String> analyze(Class<?> enumClass) {
        System.out.println("-------- Analyzing " + enumClass + " -----------");
        System.out.println("Interfaces:");
        for (Type type : enumClass.getGenericInterfaces()) {
            System.out.println(type);
        }
        System.out.println("Base " + enumClass.getSuperclass());
        System.out.println("Methods: ");
        Set<String> methods = new TreeSet<>();
        for (Method method : enumClass.getMethods()) {
            methods.add(method.getName());
        }
        System.out.println(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        // Enum子类型包含了Enum中的所有方法
        System.out.println("Explore.containsAll(Enum)?" + exploreMethods.containsAll(enumMethods));
        System.out.print("Explore.removeAll(Enum):");
        exploreMethods.removeAll(enumMethods);
        // Enum子类型多了个values()方法
        System.out.println(exploreMethods);
    }
}
