package org.lgc.tij.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试正则表达式
 * Created by laigc on 2016/12/31.
 */
public class TestRegularExpression2 {
    public static void main(String[] args) {
        String input = "Java now has regular expressions";
        Pattern p = null;
        Matcher matcher = null;
        p = Pattern.compile("^Java");
        matcher = p.matcher(input);
        while (matcher.find()) {
            System.out.print(matcher.group() + " ");
        }
        System.out.println();
    }
}
