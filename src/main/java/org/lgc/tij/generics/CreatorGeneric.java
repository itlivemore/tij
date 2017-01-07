package org.lgc.tij.generics;

/**
 * 模板方法设计模式
 * Created by laigc on 2017/1/7.
 */
abstract class GenericWithCreate<T> {
    final T element;

    GenericWithCreate() {
        element = create();
    }

    abstract T create();
}

class X {
}

class Creator extends GenericWithCreate<X> {

    @Override
    X create() {
        return new X();
    }

    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}

public class CreatorGeneric {
    public static void main(String[] args) {
        Creator creator = new Creator();
        creator.f();
    }
}
