package org.lgc.tij.holding;

import java.io.FileNotFoundException;
import java.io.IOException;

import static net.mindview.util.Print.print;

/**
 * 把“被检查的异常”转换为“不检查的异常”
 * Created by lgc on 16-12-26.
 */
class WrapCheckedException {
    void throwRuntimeException(int type) {
        try {
            switch (type) {
                case 0:
                    throw new FileNotFoundException();
                case 1:
                    throw new IOException();
                case 2:
                    throw new RuntimeException("where an I");
            }
        } catch (Exception e) {
            // 上面的被检查异常被捕获并包装进了RuntimeException对象
            throw new RuntimeException(e);
        }
    }
}

class SomeOtherException extends Exception {
}

public class TurnOffChecking {
    public static void main(String[] args) {
        WrapCheckedException wce = new WrapCheckedException();
        // 调用throwRuntimeException()方法不用使用try-catch，因为没有抛出异常
        wce.throwRuntimeException(3);

        for (int i = 0; i < 4; i++) {
            try {
                if (i < 3) {
                    wce.throwRuntimeException(i);
                } else {
                    throw new SomeOtherException();
                }
            } catch (SomeOtherException e) {
                print("SomeOtherException " + e);
            } catch (RuntimeException e) {
                // 如果要捕获原来被包装的异常，还是可以用try-catch来捕获，并用getCause()来获得原来的异常
                try {
                    throw e.getCause();
                } catch (FileNotFoundException e1) {
                    print("FileNotFoundException " + e1);
                } catch (IOException e2) {
                    print("IOException " + e2);
                } catch (Throwable throwable) {
                    print("Throwable " + throwable);
                }
            }
        }

    }
}
