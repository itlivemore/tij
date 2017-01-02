package org.lgc.tij.typeinfo;

/**
 * 转型导致耦合度过高
 * Created by laigc on 2017/1/1.
 */
class B implements A {

    @Override
    public void f() {
    }

    public void g() {
    }
}

public class InterfaceViolation {
    public static void main(String[] args) {
        A a = new B();
        a.f();
//        a.g();// 错误
        System.out.println(a.getClass().getSimpleName());
        if (a instanceof B) {
            // a被当作B实现,转型为B，能够调用B的方法，增强了耦合性
            B b = (B) a;
            b.g();
        }
    }
}
