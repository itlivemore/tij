package org.lgc.tij.strings;

import java.util.Scanner;

/** Java SE5新增了Xcanner类，它可以大减轻扫描输入的工作负担
 * Created by laigc on 2016/12/31.
 */
public class BetterRead {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(SimpleRead.input);
        System.out.println("What is your name?");
        String name = stdin.nextLine();
        System.out.println(name);
        System.out.println("How old are you? What is your favorite double?");
        System.out.println("input:<age> <double>");
        int age = stdin.nextInt();
        double favorite =  stdin.nextDouble();
        System.out.printf("Hi %s.\n", name);
        System.out.printf("In 5 years you will be %d.\n", age + 5);
        System.out.printf("My favorite double is %f.", favorite / 2);
    }
}
