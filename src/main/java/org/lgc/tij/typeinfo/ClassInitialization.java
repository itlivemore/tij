package org.lgc.tij.typeinfo;

import java.util.Random;

/**
 * 类初始化
 * Created by laigc on 2017/1/1.
 */
class Initable {
    static final int staticFinal = 47; //编译期常量，不需要对类进行初始化就可以被读取
    // 下面的不是编译期常量，直接访问将强制进行类的初始化
    static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);

    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    /* 如果一个static域不是final的，那么在对它访问时，总是要求在它被读取之前，要先进
     行链接(为这个域分配存储空间)和初始化(初始化该存储空间)*/
    static int staticNoFinal = 147;

    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    static int staticNoFinal = 74;

    static {
        System.out.println("Initializing Initable3");
    }
}

public class ClassInitialization {
    public static Random rand = new Random(47);

    public static void main(String[] args) {
        // .class的形式获得Class，类字面常量
        // 使用.class来获得对类的引用不会引发初始化
        Class initable = Initable.class;
        System.out.println("After creating Initable ref");
        System.out.println(Initable.staticFinal);
        System.out.println(Initable.staticFinal2);

        System.out.println(Initable2.staticNoFinal);

        try {
            // Class.forName()会引发类的初始化
            Class initable3 = Class.forName("org.lgc.tij.typeinfo.Initable3");
            System.out.println("After creating Initable3 ref");
            System.out.println(Initable3.staticNoFinal);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
