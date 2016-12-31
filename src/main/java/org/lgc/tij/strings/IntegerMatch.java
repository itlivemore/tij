package org.lgc.tij.strings;

/**
 * 在正则表达式中，用\d表示一位数字
 * 在其他语言中，\\表示“我要在正则表达式中插入一个普通的（字面上的）反斜线，请不要给它任何特殊的含义”。
 * 而在java中，\\的意思是“我要插入一个正则表达式的反斜线，所以其后的字符具有特殊的意义”
 * 例如：如果你想表示一个数字，那么正则表达式应该是\\d。如果你想插入一个普通的反斜线，则应该这样\\\\。
 * 不过换行和制表符之类的东西只需使用单反斜线：\n\t
 * Created by laigc on 2016/12/31.
 */
public class IntegerMatch {
    public static void main(String[] args) {
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("5678".matches("-?\\d+"));
        System.out.println("+911".matches("-?\\d+"));
        // 在正则表达式中，括号有着将表达式分组的效果，而竖直线则表示“或”操作
        System.out.println("+911".matches("(-|\\+)?\\d+"));
    }
}
