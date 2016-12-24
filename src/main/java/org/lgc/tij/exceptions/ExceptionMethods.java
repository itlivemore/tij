package org.lgc.tij.exceptions;

import static net.mindview.util.Print.print;

/**
 * 异常信息
 * Created by laigc on 2016/12/24.
 */
public class ExceptionMethods {
    public static void main(String[] args) {
        try {
            throw new Exception("My Exception");
        } catch (Exception e) {
            print("Caught Exception");
            print("getMessage(): " + e.getMessage());
            print("getLocalizedMessage(): " + e.getLocalizedMessage());
            print("toString(): " + e);
            print("printStackTrace(): ");
            e.printStackTrace(System.out);
            // 可以发现每个方法都比前一个提供了更多的信息，实际上它们每一个是前一个的超集
        }
    }
}
