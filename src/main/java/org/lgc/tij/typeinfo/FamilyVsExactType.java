package org.lgc.tij.typeinfo;

/**
 * instanceof与Class的等价性
 * instanceof与isInstance()生成的结果完全一样,equals()和==也一样
 * instanceof保持了类型的概念，它指的是“你是这个类吗，或者你是这个类的派生类吗？”
 * 而如果用==比较实际的Class对象，就没有考虑继承——它或者是这个确切的类型，或者不是
 * Created by laigc on 2017/1/1.
 */
class Base {
}

class Derived extends Base {
}

public class FamilyVsExactType {
    static void test(Object x) {
        System.out.println("Testing x of type " + x.getClass());
        System.out.println("x instanceof Base " + (x instanceof Base));
        System.out.println("x instanceof Derived " + (x instanceof Derived));
        System.out.println("Base.isInstance(x) " + Base.class.isInstance(x));
        System.out.println("Derived.isInstance(x) " + Derived.class.isInstance(x));
        System.out.println("x.getClass() == Base.class " + (x.getClass() == Base.class));
        System.out.println("x.getClass() == Derived.class " + (x.getClass() == Derived.class));
        System.out.println("x.getClass().equals(Base.class) " + x.getClass().equals(Base.class));
        System.out.println("x.getClass().equals(Derived.class) " + x.getClass().equals(Derived.class));
    }

    public static void main(String[] args) {
        test(new Base());
        test(new Derived());
    }
}
