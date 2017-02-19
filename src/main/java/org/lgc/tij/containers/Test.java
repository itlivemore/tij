package org.lgc.tij.containers;

/**
 * 测试类
 * Created by laigc on 2017/2/19.
 */
public abstract class Test<C> {
    String name;

    public Test(String name) {
        this.name = name;
    }

    abstract int test(C container, TestParam testParam);
}
