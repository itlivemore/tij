package org.lgc.tij.enumerated;

/**
 * 覆盖常量相关的方法
 * Created by laigc on 2017/3/5.
 */
public enum OverrideConstantSpecific {
    NUT, BOLT, WASHER {
        @Override
        void f() {
            System.out.println("Overriden method");
        }
    };

    void f() {
        System.out.println("default behavior");
    }

    public static void main(String[] args) {
        for (OverrideConstantSpecific ocs : values()) {
            System.out.print(ocs + ":");
            ocs.f();
        }
    }
}
