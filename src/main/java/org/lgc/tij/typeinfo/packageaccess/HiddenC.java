package org.lgc.tij.typeinfo.packageaccess;

import org.lgc.tij.typeinfo.A;

/**
 * 防止转型
 * Created by laigc on 2017/1/2.
 */
class C implements A {

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

public class HiddenC {
    public static A makeA() {
        return new C();
    }
}
