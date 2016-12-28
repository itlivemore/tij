package org.lgc.tij.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * 无意识的递归
 * Created by lgc on 16-12-27.
 */
public class InfiniteRecursion {
    @Override
    public String toString() {
        // 当要打印对象的地址时，可能会使用this关键字
        // 当用字符串拼接this时，会把this转成字符串
        // 怎样转成字符串呢？调用toString()方法转，这样就发生了递归
        return "InfiniteRecursion address: " + this + "\n";
    }

    public static void main(String[] args) {
        List<InfiniteRecursion> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new InfiniteRecursion());
        }
        System.out.println(list);
    }
}
