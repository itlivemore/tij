package org.lgc.tij.arrays;

import java.util.Arrays;

/** 多维数组
 *  Arrays.deepToString()方法，可以将多维数组转换为多个String
 * Created by laigc on 2017/2/12.
 */
public class MultidimensionalPrimitiveArray {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3},
                {4, 5, 6}};
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.deepToString(a));

        int[][][] b = new int[2][2][4];
        System.out.println(Arrays.deepToString(b));
    }
}
