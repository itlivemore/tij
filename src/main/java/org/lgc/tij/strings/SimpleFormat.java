package org.lgc.tij.strings;

/** 格式化输出字符串：format和printf,两个是等价
 * Created by lgc on 16-12-28.
 */
public class SimpleFormat {
    public static void main(String[] args) {
        int x = 5;
        double y = 3.265987;
        System.out.println("Row 1: [" + x + " " + y + "]");
        System.out.format("Row 1: [%d %f]\n", x, y);
        System.out.printf("Row 1: [%d %f]\n", x, y);
    }
}
