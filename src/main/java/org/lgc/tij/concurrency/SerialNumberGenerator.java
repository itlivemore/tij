package org.lgc.tij.concurrency;

/**
 * 一个产生序列数字的类
 * 每当nextSerialNumber()被调用时，它必须向调用者返回唯一的值
 * Created by laigc on 2017/3/19.
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;

    public static int nextSerialNumber() {
        return serialNumber++; // 非线程安全
    }
}
