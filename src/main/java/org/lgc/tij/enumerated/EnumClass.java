package org.lgc.tij.enumerated;

/**
 * Enum的一些方法
 * Created by laigc on 2017/3/4.
 */
enum Shrubbery {
    GROUND, CRAWLING, HANGING
}

public class EnumClass {
    public static void main(String[] args) {
        for (Shrubbery s : Shrubbery.values()) {
            System.out.println(s + " ordinal: " + s.ordinal()); // 返回int，enum中的次序
            // Enum类实现了Comparable接口
            System.out.print(s.compareTo(Shrubbery.CRAWLING) + " ");
            System.out.print(s.equals(Shrubbery.CRAWLING) + " ");
            System.out.println(s == Shrubbery.CRAWLING);
            System.out.println(s.getDeclaringClass()); // 返回所属的类
            System.out.println(s.name()); // 返回名字
            System.out.println("------------------------");
        }
        for (String s : "HANGING CRAWLING GROUND".split(" ")) {
            // valueOf()，根据给定的名字返回相应的enum实现(类似于Map中根据key来找值)
            // 如果给出的名字在Enum中找不到，会抛出异常
            Shrubbery shrubbery = Enum.valueOf(Shrubbery.class, s);
            System.out.println(shrubbery);
        }
        // 下面的会有异常，因为Shrubbery不存在名称为test的枚举类型
//        Enum.valueOf(Shrubbery.class, "test");
    }
}
