package org.lgc.tij.strings;

/**
 * 匹配Rudolph
 * Created by laigc on 2016/12/31.
 */
public class Rudolph {
    public static void main(String[] args) {
        // 下列的正则表达式都能匹配到Roudolph
        for (String pattern : new String[]{"Rudolph", "[rR]udolph", "[rR][aeiou][a-z]ol.*", "R.*"}) {
            System.out.println("Rudolph".matches(pattern));
        }
    }
}
