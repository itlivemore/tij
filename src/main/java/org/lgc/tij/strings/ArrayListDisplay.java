package org.lgc.tij.strings;

import org.lgc.tij.generics.coffee.Coffee;
import org.lgc.tij.generics.coffee.CoffeeGenerator;

import java.util.ArrayList;

/**容器的打印
 * Created by lgc on 16-12-27.
 */
public class ArrayListDisplay {
    public static void main(String[] args) {
        ArrayList<Coffee> coffees = new ArrayList<>();
        for (Coffee coffee : new CoffeeGenerator(10)) {
            coffees.add(coffee);
        }
        //打印ArrayList会调用toString()方法，它会遍历ArrayList中包含的所有对象，调用每个元素的toString()方法
        // 具体参见AbstractCollection的toString()方法
        System.out.println(coffees);
    }
}
