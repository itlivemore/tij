package org.lgc.tij.typeinfo;

/**
 * 匿名类也能够被反射调用方法
 * Created by laigc on 2017/1/2.
 */
class AnonymousA {
    public static A makeA() {
        return new A() {
            @Override
            public void f() {
                System.out.println("AnonymousA.f");
            }

            public void g() {
                System.out.println("C.g");
            }

            void u() {
                System.out.println("C.u");
            }

            protected void v() {
                System.out.println("C.v");
            }

            private void w() {
                System.out.println("C.w");
            }
        };
    }
}

public class AnonymousImplementation {
    public static void main(String[] args) throws Exception {
        A a = AnonymousA.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        HiddenImplementation.callHiddenMethod(a, "g");
        HiddenImplementation.callHiddenMethod(a, "u");
        HiddenImplementation.callHiddenMethod(a, "v");
        HiddenImplementation.callHiddenMethod(a, "w");
    }
}
