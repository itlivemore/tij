package org.lgc.tij.generics;

import java.util.List;

/**
 * 超类型通过符 <? super myClass>
 * 可以向Collection添加数据了
 * * Created by laigc on 2017/1/8.
 */
public class SuperTypeWildcards {
    static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());
//        apples.add(new Fruit());
    }
}
