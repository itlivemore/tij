package org.lgc.tij.generics;

import net.mindview.util.Generator;
import org.lgc.tij.generics.coffee.Coffee;
import org.lgc.tij.generics.coffee.CoffeeGenerator;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 用于Generator的泛型方法
 * Created by laigc on 2017/1/2.
 */
public class Generators {
    public static <T> Collection<T> fill(Collection<T> collection, Generator<T> generator, int n) {
        for (int i = 0; i < n; i++) {
            collection.add(generator.next());
        }
        return collection;
    }

    public static void main(String[] args) {
        Collection<Coffee> coffees = fill(new ArrayList<>(), new CoffeeGenerator(), 4);
        for (Coffee coffee : coffees) {
            System.out.println(coffee);
        }
        Collection<Integer> fnumbers = fill(new ArrayList<>(), new Fibonacci(), 18);
        for (Integer fnumber : fnumbers) {
            System.out.print(fnumber + ", ");
        }
    }
}
