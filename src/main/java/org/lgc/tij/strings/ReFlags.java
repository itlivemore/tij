package org.lgc.tij.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Patter标记
 * Created by laigc on 2016/12/31.
 */
public class ReFlags {
    public static void main(String[] args) {
        // |组合多个标记,这里匹配所有以java,Java等开头的行
        Pattern p = Pattern.compile("^java", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
//        p = Pattern.compile("^java", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher("java has regex\nJava has regex\n" +
                "JAVA has pretty good regular expressions\n" +
                "Regular expressions are in java");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
