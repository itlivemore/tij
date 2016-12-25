package org.lgc.tij.exceptions;

import static net.mindview.util.Print.print;

/**
 * 在异常没有被当前的异常处理程序捕获的情况下，异常处理机制会在跳到更高一层的异常处理程序之前，执行finally子句
 * Created by laigc on 2016/12/25.
 */
class FourExcepiton extends Exception {
}

public class AlwaysFinally {
    public static void main(String[] args) {
        print("Entering first try block");
        try {
            print("Entering second try bolck");
            try {
                // 虽然没有捕获异常，但是下面的finally子句执行了
                throw new FourExcepiton();
            } finally {
                print("finally in 2nd try block");
            }
        } catch (FourExcepiton e) {
            // 前面没有捕获的异常在这里捕获到了
            System.out.println("Caught FourException in 1st try block");
        } finally {
            System.out.println("finally in 1st try block");
        }
    }
}
