package org.lgc.tij.strings;

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 正则表达式处理文件，类似于unix的grep
 * Created by laigc on 2016/12/31.
 */
public class JGrep {
    public static void main(String[] args) {
        String filePath = "src/main/java/org/lgc/tij/strings/JGrep.java";
        // 匹配以[Ssct]开头的单词
        String regex = "\\b[Ssct]\\w+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher("");
        int index = 0;
        for (String s : new TextFile(filePath)) {
            m.reset(s);
            while (m.find()) {
                System.out.println(index++ + ": " + m.group() + ": " + m.start());
            }
        }
    }
}
