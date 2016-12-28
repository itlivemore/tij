package org.lgc.tij.strings;

import java.io.PrintStream;
import java.util.Formatter;

/**
 * 在java中，所有新的格式化功能都由java.util.Formatter类处理。
 * 可以将Formatter看作一个翻译器，它将你的格式化字符串与数据翻译成需要的结果。
 * 当你创建一个Formatter对象的时候，需要向其构造器传递一些信息，告诉它最终的结果将向哪里输出
 * Created by lgc on 16-12-28.
 */
public class Turtle {
    private String name;
    private Formatter formatter;

    public Turtle(String name, Formatter formatter) {
        this.name = name;
        this.formatter = formatter;
    }

    public void move(int x, int y) {
        formatter.format("%s is at (%d, %d)\n", name, x, y);
    }

    public static void main(String[] args) {
        PrintStream outAlias = System.out;
        Turtle tom = new Turtle("tom", new Formatter(System.out));
        Turtle jerry = new Turtle("jerry", new Formatter(outAlias));
        tom.move(1, 1);
        jerry.move(2, 3);
        tom.move(3, 6);
        jerry.move(7, 9);
    }
}
