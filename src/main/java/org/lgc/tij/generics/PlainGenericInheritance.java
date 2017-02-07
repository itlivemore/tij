package org.lgc.tij.generics;

/**
 * 不使用自限定类型，普通的继承机制就会介入，能够实现重载
 * Created by lgc on 17-2-7.
 */
class GenericSetter<T> {
    void set(T arg) {
        System.out.println("GenericSetter.set(Base)");
    }
}

class DerivedGS extends GenericSetter<Base> {
    // 注意这里只是重载了方法，并没有覆盖
    void set(Derived derived) {
        System.out.println("DerivedGS.set(Derived)");
    }
}

public class PlainGenericInheritance {
    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedGS derivedGS = new DerivedGS();
        derivedGS.set(base);
        derivedGS.set(derived);
    }
}
