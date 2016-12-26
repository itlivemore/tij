package org.lgc.tij.exceptions;

/**
 * printStackTrace()方法所提供的信息可以通过getStackTrace()方法来直接访问
 * 这个方法将返回由栈轨迹中的元素所构成的数组，其中每一个元素都表示栈中的一帧。
 * 元素0是栈顶元素，并且是调用序列中的最后一个方法调用（这个Throwable被创建和抛出的之处）
 * 数组中最后一个元素和栈底是调用序列中的第一个方法调用。
 * Created by laigc on 2016/12/24.
 */
public class WhoCalled {
    static void f() {
        try {
            throw new Exception();
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                System.out.println(stackTraceElement.getMethodName());
            }
        }
    }

    static void g() {
        f();
    }

    static void h() {
        g();
    }

    public static void main(String[] args) {
        f();
        System.out.println("----------------------------------");
        g();
        System.out.println("----------------------------------");
        h();
    }
}
