package org.lgc.tij.arrays;

import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;

import java.util.Arrays;

/**
 * 测试Generated
 * Generated能够创建并填充数组
 * Created by laigc on 2017/2/12.
 */
public class TestGenerated {
    public static void main(String[] args) {
        Integer[] a = {6, 7, 8, 9};
        System.out.println(Arrays.toString(a));
        a = Generated.array(a, new CountingGenerator.Integer());
        System.out.println(Arrays.toString(a));

        Integer[] b = Generated.array(Integer.class, new CountingGenerator.Integer(), 15);
        System.out.println(Arrays.toString(b));
    }
}
