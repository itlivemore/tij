package org.lgc.tij.arrays;

import net.mindview.util.CountingGenerator;
import net.mindview.util.Generator;

/**
 * 测试CountingGenerator
 * class.getClasses()返回类定义的公共的内部类,以及从父类、父接口那里继承来的内部类
 * CountingGenerator能够生成各种类型数据
 * Created by laigc on 2017/2/12.
 */
public class GeneratorsTest {
    public static int size = 10;

    public static void test(Class<?> surroundingClass) {
        for (Class<?> type : surroundingClass.getClasses()) {
            System.out.print(type.getSimpleName() + " : ");
            try {
                Generator<?> generator = (Generator<?>) type.newInstance();
                for (int i = 0; i < size; i++) {
                    System.out.print(generator.next() + " ");
                }
                System.out.println();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        test(CountingGenerator.class);
    }
}
