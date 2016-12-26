package org.lgc.tij.exceptions;

import static net.mindview.util.Print.print;

/**
 * 自定义异常，添加成员变量及自定义构造方法
 * Created by laigc on 2016/12/24.
 */
class MyException2 extends Exception {
    private int x;

    public MyException2() {
    }

    public MyException2(String msg) {
        super(msg);
    }

    public MyException2(String msg, int x) {
        super(msg);
        this.x = x;
    }

    public int val() {
        return x;
    }

    public String getMessage() {
        return "Detail Message: " + x + " " + super.getMessage();
    }
}

public class ExtraFeatures {
    public static void f() throws MyException2 {
        print("Throwing MyException2 from f()");
        throw new MyException2();
    }

    public static void g() throws MyException2 {
        print("Throwing MyExceptionw from g()");
        throw new MyException2("Originated in g()");
    }

    public static void h() throws MyException2 {
        print("Throwing MyExceptionw from h()");
        throw new MyException2("Originated in h()", 47);
    }

    public static void main(String[] args) {
        try {
            f();
        } catch (MyException2 e) {
            e.printStackTrace();
        }
        try {
            h();
        } catch (MyException2 e) {
            e.printStackTrace();
        }
        try {
            h();
        } catch (MyException2 e) {
            e.printStackTrace();
            System.out.println("e.val = " + e.val());
        }
    }
}
