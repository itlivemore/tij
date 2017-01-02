package org.lgc.tij.generics;

/**
 * 一个只能持有单个对象的类，明确指定了持有对象的类型
 * Created by laigc on 2017/1/2.
 */
class Automobile {
}

public class Holder1 {
    private Automobile a;

    public Holder1(Automobile a) {
        this.a = a;
    }

    public Automobile getA() {
        return a;
    }
}
