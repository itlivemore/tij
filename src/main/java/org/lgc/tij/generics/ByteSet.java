package org.lgc.tij.generics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 泛型中不能使用基本类型，但是java有自动包装机制
 * Created by laigc on 2017/1/8.
 */
public class ByteSet {
    public static void main(String[] args) {
        Byte[] bytes1 = {1, 2, 3, 4, 5, 6};
        Set<Byte> mySet = new HashSet<>(Arrays.asList(bytes1));
//        List<Byte> bytes = Arrays.<Byte>asList(1, 2, 3, 4, 5, 6);
        List<Integer> integers = Arrays.<Integer>asList(1, 2, 3, 4, 5, 6);
    }
}
