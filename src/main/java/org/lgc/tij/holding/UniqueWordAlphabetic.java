package org.lgc.tij.holding;

import net.mindview.util.CountingGenerator;
import net.mindview.util.TextFile;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by lgc on 16-12-20.
 */
public class UniqueWordAlphabetic {
    public static void main(String[] args) {
        Set<String> words = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);//TreeSet是有序的，可以传入比较器
        words.addAll(new TextFile("pom.xml"));
        System.out.println(words);
    }
}
