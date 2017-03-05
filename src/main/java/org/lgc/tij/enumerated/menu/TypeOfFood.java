package org.lgc.tij.enumerated.menu;

import static org.lgc.tij.enumerated.menu.Food.MainCourse;

/**
 * Food中的所有enum都是Food
 * Created by laigc on 2017/3/5.
 */
public class TypeOfFood {
    public static void main(String[] args) {
        Food food = Food.Appetizer.SALAD;
        food = MainCourse.LASAGNE;
        food = Food.Dessert.GELATO;
        food = Food.Coffee.CAPPUCCINO;
    }
}
