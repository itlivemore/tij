package org.lgc.tij.generics;

/**
 * 协变返回类型
 * Created by lgc on 17-2-7.
 */

class Base {
}

class Derived extends Base {
}

interface OrdinaryGetter {
    Base get();
}

interface DerivedGetter extends OrdinaryGetter {
    @Override
    Derived get();// 这里覆盖了OrdinaryGetter的get()方法，并且将返回类型修改成了原get()方法返回的类型的子类型
}

public class CovariantReturnTypes {
    void test(DerivedGetter d) {
        Derived derived = d.get();
    }
}
