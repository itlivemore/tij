package org.lgc.tij.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 组是用括号划分的正则表达式，可以根据组的编号来引用某个组。组号为0表示整个表达式，组号1表示被第一对括号括起的组，依此类推
 * 如表达式:A(B(C))D中有三个组：组0是ABCD，组1是BC，组2是C
 * Matcher对象提供了一系列方法，用以获取与组相关的信息
 * public int groupCount()返回该匹配器的模式中的分组数目，第0组不包括在内。
 * public String group()返回前一次匹配操作(例如find())的第0组(整个匹配)
 * public String group(int i)返回在前一次匹配操作期间指定的组号，如果匹配成功，但是指定的组没有匹配输入的字符串的任何部分，则将返回null
 * public int start(int group)返回在前一次匹配操作中寻找到的组的起始索引
 * public  int end(int group)返回在前一次匹配操作中寻找到的最后一个字符索引加一的值
 * Created by laigc on 2016/12/31.
 */
public class Groups {
    static public final String POEM =
            "Twas brillig, and the slithy toves\n" +
                    "Did gyre and gimble in the wabe.\n" +
                    "All mimsy were the borogoves,\n" +
                    "And the mome raths outgrabe.\n\n" +
                    "Beware the Jabberwock, my son,\n" +
                    "The jaws that bite, the claws that catch.\n" +
                    "Beware the Jubjub bird, and shun\n" +
                    "The frumious Bandersnatch.";

    public static void main(String[] args) {
        // 匹配每行的最后三个单词，(?m)表示模式标记
        Matcher matcher = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$").matcher(POEM);
        while (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                System.out.print("第" + i + "组:" + matcher.group(i) + " ");

            }
            System.out.println();
        }
    }
}
