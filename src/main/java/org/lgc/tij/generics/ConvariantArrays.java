package org.lgc.tij.generics;

/** 导出类型的数组赋予基类型的数组引用
 * Created by laigc on 2017/1/8.
 */

class Fruit {
}

class Apple extends Fruit {
}

class Jonathan extends Apple {
}

class Orange extends Fruit {
}

public class ConvariantArrays {
    public static void main(String[] args) {
        // 可以将子类数组引用赋给父类数组引用
        Fruit[] fruits = new Apple[10];
        fruits[0] = new Apple();
        fruits[1] = new Jonathan();

        try {
            // 实际上是Apple数组
            fruits[0] = new Fruit();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            fruits[1] = new Orange();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
