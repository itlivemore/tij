package org.lgc.tij.generics;

/**
 * 非泛型代码中，参数类型不能随子类型发生变化，会发生重载
 * Created by lgc on 17-2-7.
 */
class OrdinarySetter {
    void set(Base base) {
        System.out.println("OrdinarySetter.set(Base)");
    }
}

class DerivedSetter extends OrdinarySetter {
    // 注意这里不是覆盖，而是重载
    void set(Derived derived) {
        System.out.println("DerivedSetter.set(Derived)");
    }
}

public class OrdinaryArguments {
    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedSetter ds = new DerivedSetter();
        ds.set(derived);
        ds.set(base);
    }
}
