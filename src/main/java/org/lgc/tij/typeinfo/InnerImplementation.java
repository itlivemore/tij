package org.lgc.tij.typeinfo;

/**
 * 将接口实现为一个私有内部类，反射仍旧能够调用其方法
 * Created by laigc on 2017/1/2.
 */
class InnerA {
    private static class C implements A {

        @Override
        public void f() {
            System.out.println("C.f");
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
    }

    public static A makeA() {
        return new C();
    }
}

public class InnerImplementation {
    public static void main(String[] args) throws Exception {
        A a = InnerA.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        HiddenImplementation.callHiddenMethod(a, "g");
        HiddenImplementation.callHiddenMethod(a, "u");
        HiddenImplementation.callHiddenMethod(a, "v");
        HiddenImplementation.callHiddenMethod(a, "w");
    }
}
