package org.lgc.tij.enumerated.menu;

/**
 * 随机选择Course中的Food
 * Created by laigc on 2017/3/5.
 */
public class Meal {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (Course course : Course.values()) {
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("------");
        }

        int length = Course.values().length;
        System.out.println(length);
    }
}
