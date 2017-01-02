package org.lgc.tij.typeinfo;

import org.lgc.tij.typeinfo.packageaccess.HiddenC;

import java.lang.reflect.Method;

/**
 * 试图转型会失败
 * Created by laigc on 2017/1/2.
 */
public class HiddenImplementation {
    static void callHiddenMethod(Object a, String methodName) throws Exception {
        Method method = a.getClass().getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(a);
    }

    public static void main(String[] args) throws Exception {
        A a = HiddenC.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        // 无法访问到C，因为C是包访问权限
        /*
        if (a instanceof C) {
           C c = (C) a;
           c.g();
        }*/
        // 但是通过反射可以调用方法
        callHiddenMethod(a, "g");
        callHiddenMethod(a, "u");
        callHiddenMethod(a, "v");
        callHiddenMethod(a, "w");
    }
}
