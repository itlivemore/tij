package org.lgc.tij.enumerated;

import java.util.EnumSet;

/**
 * EnumSet的使用
 * EnumSet类似于Set，元素必须来自于一个enum
 * Created by laigc on 2017/3/5.
 */
public class EnumSets {
    public static void main(String[] args) {
        // 创建空的EnumSet
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);
        System.out.println(points); // Empty set

        points.add(AlarmPoints.BATHROOM);
        System.out.println(points);

        points.addAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));
        System.out.println(points);

        // 从enum所有元素创建
        points = EnumSet.allOf(AlarmPoints.class);
        System.out.println(points);
        points.removeAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));
        System.out.println(points);
        // complementOf(param)返回没有包含在param中的其他元素
        points = EnumSet.complementOf(points);
        System.out.println(points);
    }
}
