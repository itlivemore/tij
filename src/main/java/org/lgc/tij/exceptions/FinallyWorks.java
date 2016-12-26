package org.lgc.tij.exceptions;

/**
 * 无论try里面的语句是否抛出异常，finally里的语句都会执行
 * Created by laigc on 2016/12/25.
 */
class ThreeException extends Exception {
}

public class FinallyWorks {
    static int count = 0;

    public static void main(String[] args) {
        while (true) {
            try {
                if (count++ == 0) {
                    throw new ThreeException();
                }
                System.out.println("No Exception");// 前面抛出异常，该语句不会执行
            } catch (ThreeException e) {
                System.out.println("Caught ThreeException");
            } finally {
                // 无论try语句是否抛出异常，都会执行
                System.out.println("In finally clause");
                if (count == 2) {
                    break;
                }
            }
        }
    }
}
