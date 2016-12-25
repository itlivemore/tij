package org.lgc.tij.exceptions;

/**
 * 当要把除内存之外的资源恢复到它们的初始状态时，就要用到finally子句
 * 这种需要清理的资源包括：已经打开的文件或网络连接，在屏幕上画的图形，甚至可以是外部世界的某个开关
 * Created by laigc on 2016/12/25.
 */
public class WithFinally {
    private static Switch sw = new Switch();

    public static void f() throws OnOffException1, OnOffException2 {
    }

    public static void main(String[] args) {
        try {
            sw.on();
            f();
        } catch (OnOffException1 onOffException1) {
            onOffException1.printStackTrace();
        } catch (OnOffException2 onOffException2) {
            onOffException2.printStackTrace();
        } finally {
            sw.off();
        }
    }
}
