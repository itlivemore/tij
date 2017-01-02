package org.lgc.tij.generics.watercolors;

import net.mindview.util.Sets;

import java.util.EnumSet;
import java.util.Set;

/**
 * Set取并集，交集等
 * Created by laigc on 2017/1/2.
 */
public class WaterColorSets {
    public static void main(String[] args) {
        Set<Watercolor> set1 = EnumSet.range(Watercolor.BRILLIANT_RED, Watercolor.VIRIDIAN_HUE);
        Set<Watercolor> set2 = EnumSet.range(Watercolor.CERULEAN_BLUE_HUE, Watercolor.BURNT_UMBER);
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);
        System.out.println(Sets.union(set1, set2));// 并集
        Set<Watercolor> subSet = Sets.intersection(set1, set2);// 交集
        System.out.println(subSet);
        System.out.println(Sets.difference(set2, subSet));// 从set2中移除subSet中包含的元素
        System.out.println(Sets.complement(set1, set2)); // 返回set1中包含除了交集之外的所有元素


    }
}
