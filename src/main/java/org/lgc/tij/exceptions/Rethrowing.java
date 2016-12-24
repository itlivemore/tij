package org.lgc.tij.exceptions;

/**
 * Created by laigc on 2016/12/24.
 */
public class Rethrowing {
    public static void f() throws Exception {
        System.out.println("originating the exception in f()");
        throw new Exception("thrown from f()");
    }

    public static void g() throws Exception {
        try {
            f();
        } catch (Exception e) {
            System.out.println("Inside g() e.printStackTrace()");
            e.printStackTrace(System.out);
            throw e;
        }
    }

    public static void h() throws Exception {
        try {
            f();
        } catch (Exception e) {
            System.out.println("Inside h() e.printStackTrace()");
            e.printStackTrace(System.out);
            //这里调用e.fillInStackTrace()，这一行就又成了异常的新发生地了
            throw (Exception) e.fillInStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            g();
        } catch (Exception e) {
            System.out.println("main: printStackTrace()");
            e.printStackTrace(System.out);
        }
        System.out.println("------------------------------------");
        try {
            h();
        } catch (Exception e) {
            System.out.println("main:printStackTrace()");
            // 控制台打印并没有显示f()调用异常，而是h()调用异常
            e.printStackTrace(System.out);
        }
    }
}
