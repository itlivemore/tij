package org.lgc.tij.containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成hasCode示例
 * Created by laigc on 2017/2/19.
 */
public class CountedString {
    private static List<String> created = new ArrayList<>();

    private String s;
    private int id = 0;

    public CountedString(String string) {
        s = string;
        created.add(string);
        for (String s2 : created) {
            if (s2.equals(s)) {
                id++;
            }
        }
    }

    @Override
    public String toString() {
        return "String: " + s + " id: " + " hashCode() : " + hashCode();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + s.hashCode();
        result = 37 * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CountedString)) {
            return false;
        }
        CountedString countedString2 = (CountedString) obj;
        return s.equals(countedString2.s) && id == countedString2.id;
    }

    public static void main(String[] args) {
        Map<CountedString, Integer> map = new HashMap<>();
        CountedString[] countedStrings = new CountedString[5];
        for (int i = 0; i < countedStrings.length; i++) {
            countedStrings[i] = new CountedString("hi");
            map.put(countedStrings[i], i);
        }
        System.out.println(map);

        for (CountedString countedString : countedStrings) {
            System.out.println("Looking up " + countedString);
            System.out.println(map.get(countedString));
        }
    }
}
