package org.lgc.tij.containers;

import net.mindview.util.CollectionData;
import net.mindview.util.RandomGenerator;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 使用随机数据填充容器
 * Created by laigc on 2017/2/12.
 */
public class CollectionDataGeneration {
    public static void main(String[] args) {
        System.out.println(new ArrayList<String>(CollectionData.list(new RandomGenerator.String(9), 10)));
        System.out.println(new HashSet<Integer>(CollectionData.list(new RandomGenerator.Integer(), 10)));
    }
}
