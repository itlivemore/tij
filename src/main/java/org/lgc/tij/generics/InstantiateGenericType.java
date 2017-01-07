package org.lgc.tij.generics;

/**
 * 创建泛型对象,new T
 * 通过传入的Class对象来创建
 * Created by laigc on 2017/1/7.
 */
class ClassAsFactory<T> {
    T x;

    public ClassAsFactory(Class<T> kind) {
        try {
            x = kind.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class Employee {
}

public class InstantiateGenericType {
    public static void main(String[] args) {
        ClassAsFactory<Employee> employeeFactory = new ClassAsFactory<>(Employee.class);
        System.out.println("new ClassAsFactory<>(Employee.class) succeeded");
        try {
            ClassAsFactory<Integer> integerClassAsFactory = new ClassAsFactory<>(Integer.class);
        } catch (Exception e) {
            // 因为Integer没有无参的构造方法，所以这里会有异常
            e.printStackTrace();
        }

    }
}
