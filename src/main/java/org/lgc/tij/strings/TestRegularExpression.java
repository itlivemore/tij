package org.lgc.tij.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 测试正则表达式
 * Created by laigc on 2016/12/31.
 */
public class TestRegularExpression {
    public static void main(String[] args) {
        String input = "abcabcabcdefabc";
        Pattern pattern = Pattern.compile("abc");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            System.out.println("Match \"" + matcher.group() + "\" at positions " +
            matcher.start() + "-" + (matcher.end() - 1));
        }
    }
}
