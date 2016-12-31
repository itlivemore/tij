package org.lgc.tij.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 组的start()与end()
 * 在匹配操作成功之后，start()返回先前匹配的起始位置的索引，而end()返回所匹配的最后字符的索引加一值
 * 匹配操作失败之后（或先于一个正在进行的匹配操作去尝试）调用start()或end()将会产生IllegalStateException。
 * 注意：find()可以在输入的任意位置定位正则表达式，
 * 而lookingAt()和matches()只有在正则表达式与输入的最开始处就开始匹配时才会成功
 * matches()只有在整个输入都匹配正则表达式时才会成功，
 * 而lookingAt()只要输入的第一部分匹配就会成功
 * Created by laigc on 2016/12/31.
 */
public class StartEnd {
    public static String input =
            "As long as there is injustice, whenever a\n" +
                    "Targathian baby cries out, wherever a distress\n" +
                    "signal sounds among the stars ... We'll be there.\n" +
                    "This fine ship, and this fine crew ...\n" +
                    "Never give up! Never surrender!";

    private static class Display {
        private boolean regexPrinted = false;
        private String regex;

        Display(String regex) {
            this.regex = regex;
        }

        void display(String message) {
            if (!regexPrinted) {
                System.out.println(regex);
                regexPrinted = true;
            }
            System.out.println(message);
        }
    }

    static void examine(String s, String regex) {
        Display d = new Display(regex);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        while (m.find()) {
            d.display("find() '" + m.group() + "' start=" + m.start() + " end=" + m.end());
        }
        if (m.lookingAt()) {
            d.display("lookingAt() start= " + m.start() + " end=" + m.end());
        }
        if (m.matches()) {
            d.display("matches() start= " + m.start() + " end= " + m.end());
        }
    }

    public static void main(String[] args) {
        for (String in : input.split("\n")) {
            System.out.println("input: " + in);
            for (String regex : new String[]{"\\w*ere\\w*", "\\w*ever", "T\\w+", "Never.*?!"}) {
                examine(in, regex);
            }
        }
    }
}
