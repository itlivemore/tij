package org.lgc.tij.holding;

import java.util.Arrays;

/**数组可以使用foreach，但是数组不是Iterable
 * Created by laigc on 2016/12/24.
 */
public class ArrayIsNotIterable {
    static <T> void test(Iterable<T> it) {
        for (T t : it
                ) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test(Arrays.asList(1, 2, 3));
        String[] strings = {"A", "B", "C"};
        for (String string : strings) {
            System.out.print(string);
        }
        System.out.println();
//        test(strings); // 数组不是Iterable
        test(Arrays.asList(strings));
    }
}
