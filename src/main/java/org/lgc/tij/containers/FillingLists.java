package org.lgc.tij.containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 填充容器
 * Created by laigc on 2017/2/12.
 */
class StringAddress {
    private String s;

    public StringAddress(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return super.toString() + " " + s;
    }
}

public class FillingLists {
    public static void main(String[] args) {
        // 使用Collections.nCopies()创建并传递给List
        List<StringAddress> list = new ArrayList<>(Collections.nCopies(4, new StringAddress("Hello")));
        System.out.println(list);
        // 使用Collections.fill()填充
        Collections.fill(list, new StringAddress("World"));
        System.out.println(list);
    }
}
