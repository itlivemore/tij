package org.lgc.tij.strings;

import java.math.BigInteger;
import java.util.Formatter;

/**
 * Formatter转换
 * d 整数型（十进制）
 * c Unicode字符
 * b Boolean值
 * s String
 * f 浮点数（十进制）
 * e 浮点数（科学计数）
 * x 整数（十六进制）
 * h 散列码（十六进制）
 * % 字符%
 * Created by lgc on 16-12-29.
 */
public class Conversion {
    public static void main(String[] args) {
        Formatter f = new Formatter(System.out);
        char u = 'a';
        System.out.println("u = 'a'");
//        f.format("d  %d\n", u);
        f.format("c: %c\n", u);
        f.format("b: %b\n", u);
        f.format("s: %s\n", u);
//        f.format("f: %f\n", u);
//        f.format("e: %e\n", u);
//        f.format("x: %x\n", u);
        f.format("h: %h\n", u);

        int v = 121;
        System.out.println("int v = 121;");
        f.format("d  %d\n", v);
        f.format("c: %c\n", v);
        f.format("b: %b\n", v);
        f.format("s: %s\n", v);
//        f.format("f: %f\n", v);
//        f.format("e: %e\n", v);
        f.format("x: %x\n", v);
        f.format("h: %h\n", v);


        BigInteger w = new BigInteger("5000000000");
        System.out.println("BigInteger w = new BigInteger(\"5000000000\");");
        f.format("d  %d\n", w);
//        f.format("c: %c\n", w);
        f.format("b: %b\n", w);
        f.format("s: %s\n", w);
//        f.format("f: %f\n", w);
//        f.format("e: %e\n", w);
        f.format("x: %x\n", w);
        f.format("h: %h\n", w);

        double x = 179.543;
        System.out.println("double x = 179.543;");
//        f.format("d  %d\n", x);
//        f.format("c: %c\n", x);
        f.format("b: %b\n", x);
        f.format("s: %s\n", x);
        f.format("f: %f\n", x);
        f.format("e: %e\n", x);
//        f.format("x: %x\n", x);
        f.format("h: %h\n", x);

        Conversion y = new Conversion();
        System.out.println("Conversion y = new Conversion();");
//        f.format("d  %d\n", y);
//        f.format("c: %c\n", y);
        f.format("b: %b\n", y);
        f.format("s: %s\n", y);
//        f.format("f: %f\n", y);
//        f.format("e: %e\n", y);
//        f.format("x: %x\n", y);
        f.format("h: %h\n", y);

        boolean z = false;
        System.out.println("boolean z = false;");
//        f.format("d  %d\n", z);
//        f.format("c: %c\n", z);
        f.format("b: %b\n", z);
        f.format("s: %s\n", z);
//        f.format("f: %f\n", z);
//        f.format("e: %e\n", z);
//        f.format("x: %x\n", z);
        f.format("h: %h\n", z);

        //注意这里都用到了对b的转换，只要对象不为Null，转换结果为true

    }
}
