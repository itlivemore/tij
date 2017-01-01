package org.lgc.tij.typeinfo;

/**
 * Class泛型
 * 普通的类引用不会产生警告信息
 * 尽管泛型类型引用只能赋值为指向其声明的类型，但是普通的类引用可以被重新赋值为指向任何其他的Class对象。
 * 通过使用泛型语法，可以让编译器强制执行额外的类型检查
 * Created by laigc on 2017/1/1.
 */
public class GenericClassReferences {
    public static void main(String[] args) {
        Class intClass = int.class; // 普通类引用
        Class<Integer> genericIntClass = int.class;// 泛型类引用
        genericIntClass = intClass; // 不会报错
        intClass = double.class;
//        genericIntClass = double.class;// 报错

    }
}
