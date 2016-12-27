package org.lgc.tij.strings;

/**
 * Created by lgc on 16-12-27.
 */
public class Concatenation {
    public static void main(String[] args) {
        String mango = "mango";
        // 直接用+连接字符串，连接字符串过程会生成多个中间字符串，有性能问题,可以使用StringBuilder来连接字符串
        String s = "abc" + mango + "def" + 47;
        System.out.println(s);
    }
}
