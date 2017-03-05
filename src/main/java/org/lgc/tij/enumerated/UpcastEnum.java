package org.lgc.tij.enumerated;

/**
 * 由于values()方法是由编译器插入到enum定义中的static方法，
 * 如果将enum实例向上转型为Enum，那么values()方法将不能访问。
 * 不过，在Class中有一个getEnumClass()方法，可以取得所有的enum实例
 * Created by laigc on 2017/3/5.
 */
enum Search {
    HITHER, YON
}

public class UpcastEnum {
    public static void main(String[] args) {
        Search[] searches = Search.values();
        Enum e = Search.HITHER; // 向上转型
//        e.values(); // 不能使用values()
        for (Enum en : e.getClass().getEnumConstants()) {
            System.out.println(en);
        }

        // 对不是枚举类调用getEnumConstants()方法,会返回null
        Class<Integer> integerClass = Integer.class;
        Integer[] enumConstants = integerClass.getEnumConstants();
        if (enumConstants == null) {
            System.out.println("enumConstants is null");
        }
    }
}
