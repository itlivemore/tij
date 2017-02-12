package org.lgc.tij.arrays;

import net.mindview.util.ConvertTo;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;

import java.util.Arrays;

/**
 * 将包装类型数组转换成基本类型数组
 * Created by laigc on 2017/2/12.
 */
public class PrimitiveConversionDemonstration {
    public static void main(String[] args) {
        Integer[] integers = Generated.array(Integer.class, new CountingGenerator.Integer(), 15);
        int[] ints = ConvertTo.primitive(integers);// 将包装类型数组转换成基本类型数组
        System.out.println(Arrays.toString(ints));
    }

}
