package org.lgc.tij.strings;

import java.util.Formatter;

/**
 * 格式化说明符
 * 抽象有语法格式如下：
 * %[argument_index$][flags][width][.precision]conversion
 * width：显示的长度，不够加空格，默认是右对齐
 * flags：-表示左对齐
 * .precision：用于小数，控制小数位数，默认是6位，小数多则舍入，太少则补0。作用于字符串时，控制字符串的最大数量
 * Created by lgc on 16-12-28.
 */
public class Receipt {
    private double total = 0;
    private Formatter formatter = new Formatter(System.out);

    private void printTitle() {
        formatter.format("%-15s %5s %10s\n", "Item", "Qty", "Price");
        formatter.format("%-15s %5s %10s\n", "----", "---", "-----");
    }

    private void print(String name, int qty, double price) {
        formatter.format("%-15.15s %5d %10.2f\n", name, qty, price);
        total += price;
    }

    private void printTotal() {
        formatter.format("%-15s %5s %10.2f\n", "Tax", "", total * 0.06);
        formatter.format("%-15s %5s %10s\n", "", "", "-----");
        formatter.format("%-15s %5s %10.2f\n", "Total", "", total * 1.06);
    }

    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.printTitle();
        receipt.print("Jack's Magic Beans", 4, 4.25);
        receipt.print("Princes Peas", 3, 5.1);
        receipt.print("Three Bears Porridge", 1, 14.29);
        receipt.printTotal();
    }
}
