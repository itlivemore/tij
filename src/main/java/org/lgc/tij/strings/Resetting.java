package org.lgc.tij.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Matcher的reset()方法
 *  通过reset()方法，可以将现有的Matcher对象应用于一个新的字符序列
 *  使用不带参数的reset()方法，可以将一Matcher对象重新设置到当前字符序列的起始位置
 * Created by laigc on 2016/12/31.
 */
public class Resetting {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with bags");
        while (m.find()) {
            System.out.print(m.group() + " ");
        }
        System.out.println();
        m.reset("fix the rig with rags");
        while (m.find()) {
            System.out.print(m.group() + " ");
        }
    }
}
