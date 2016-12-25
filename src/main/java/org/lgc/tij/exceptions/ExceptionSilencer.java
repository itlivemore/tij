package org.lgc.tij.exceptions;

/**
 * Created by laigc on 2016/12/25.
 */
public class ExceptionSilencer {
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } finally {
            // 直接在finally中return也会丢失异常信息
            return;
        }
    }
}
