package org.lgc.tij.exceptions;

import static net.mindview.util.Print.print;

/**
 * 因为finally子句总是会执行，所以在一个方法中，可以从多个点返回，并且可以保证清理工作仍会执行
 * Created by laigc on 2016/12/25.
 */
public class MultipleReturns {
    public static void f(int i) {
        print("Initialization that requires cleanup");
        try {
            print("Point 1");
            if (i == 1) {
                return;
            }
            print("Point 2");
            if (i == 2) {
                return;
            }
            print("Point 3");
            if (i == 3) {
                return;
            }
            print("End");
            return;
        } finally {
            print("Performing cleanup");
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            f(i);
        }
    }
}
