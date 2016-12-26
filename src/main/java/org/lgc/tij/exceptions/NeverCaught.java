package org.lgc.tij.exceptions;

/**
 * RuntimeException不用在代码中显示地去捕获
 * 如果RuntimeException类型的异常没有被捕获而直达main()方法,那么在程序退出前将调用异常的printStackTrace()方法
 * <p>
 * Created by laigc on 2016/12/25.
 */
public class NeverCaught {
    static void f() {
        throw new RuntimeException("From f");
    }

    static void g() {
        f();
    }

    public static void main(String[] args) {
        g();
    }
}
