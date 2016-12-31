package org.lgc.tij.strings;
// 下面是本例中要处理的字符串
/*! Here's a block of text to use as input to
    the     regular expression matcher. Note that we'll
    first   extract the block of text by looking for
    the special   delimiters, then process the
    extracted block. !*/


import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式的替换
 * Created by laigc on 2016/12/31.
 */
public class TheReplacements {
    public static void main(String[] args) {
        // 把当前java文件读入
        String s = TextFile.read("src/main/java/org/lgc/tij/strings/TheReplacements.java");
        // 获取上面的要处理的字符串
        Matcher mInput = Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL).matcher(s);
        if (mInput.find()) {
            s = mInput.group();
        }
        System.out.println(s);
        // 把两个或两个以上空格替换为一个空格
        s = s.replaceAll(" {2,}", " ");
        // 使用(?m)模式将行首的一个或多个空格去掉
        s = s.replaceAll("(?m)^ +", "");
        System.out.println(s);
        s = s.replaceFirst("[aeiou]", "(VOWEL1)");
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile("[aeiou]");
        Matcher m = p.matcher(s);
        while (m.find()) {
            // appendReplacement(StringBuffer sb,String replacement)执行渐进式的替换。它允许你调用其他方法来生成或处理replacement
            // 使你能够用编程的方式将目标分割成组，从而具备更大的替换功能
            m.appendReplacement(sb, m.group().toUpperCase());
        }
        // appendTail(StringBuffer sb)，在执行了一次或多次appendReplacement()之后，调用此方法可以将输入字符串余下的部分复制到sb中
        m.appendTail(sb);
        System.out.println(sb);
    }
}
