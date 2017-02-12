package org.lgc.tij.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建泛型数组
 * Created by laigc on 2017/2/12.
 */
public class ArrayOfGenerics {
    public static void main(String[] args) {
        List<String>[] ls;
//        ls = new List<String>[10]; // 不能这样创建泛型数组
        List[] la = new List[10];
        ls = la;
        ls[0] = new ArrayList<String>();
//        ls[1] = new ArrayList<Integer>();

        Object[] objects = ls;
        objects[1] = new ArrayList<Integer>();

        List<BerylliumSphere>[] spheres = new List[10];
        for (int i = 0; i < spheres.length; i++) {
            spheres[i] = new ArrayList<BerylliumSphere>();
        }
    }
}
