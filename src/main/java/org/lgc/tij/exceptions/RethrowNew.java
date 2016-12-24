package org.lgc.tij.exceptions;

/**
 * 重新抛出新的异常
 * Created by laigc on 2016/12/24.
 */
class OneException extends Exception {
    public OneException(String msg) {
        super(msg);
    }
}

class TwoException extends Exception {
    public TwoException(String msg) {
        super(msg);
    }
}

public class RethrowNew {
    public static void f() throws OneException {
        System.out.println("originating the exception in f() ");
        throw new OneException("thrown from f()");
    }

    public static void main(String[] args) {
        try {
            try {
                f();
            } catch (OneException e) {
                System.out.println("Caught in inner try, e.printStackTrace()");
                e.printStackTrace(System.out);
                // 在捕获异常时抛出另一个异常，这么做得到的效果类似于使用fillInStackTrace();
                // 有关原来的异常发生点的信息会丢失，剩下的是与新的抛出点有关的信息
                throw new TwoException("from inner try");
            }
        } catch (TwoException e1) {
            System.out.println("Caught in outer tyr, e.printStackTrace()");
            e1.printStackTrace(System.out);
        }
    }
}
