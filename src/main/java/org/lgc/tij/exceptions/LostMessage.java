package org.lgc.tij.exceptions;

/**
 * Created by laigc on 2016/12/25.
 */
class VeryImportantException extends Exception {
}

//HoHum单词意思：令人生厌的，厌烦的
class HoHumException extends Exception {
}

public class LostMessage {
    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }

    void dispose() throws HoHumException {
        throw new HoHumException();
    }

    public static void main(String[] args) {
        try {
            LostMessage lm = new LostMessage();
            try {
                lm.f();
            } finally {
                // 没有捕获异常，重新抛出了个异常，原有异常信息被丢失了
                lm.dispose();
            }
        } catch (Exception e) {
            System.out.println(e); // VeryImportantException异常信息丢失了
        }
    }
}
