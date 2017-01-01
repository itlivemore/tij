package org.lgc.tij.strings;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/** StringTokenizer
 *  在Java引入正则表达式和Scanner类之前，分割字符串的唯一方法是使用StringTokenizer类来分词。
 *  不过现在有了正则表达式和Scanner，我们可以使用更加简单、更加简洁的方式来完成同样的工作。
 *  本例是StringTokenizer与另两种技术的比较
 * Created by laigc on 2017/1/1.
 */
public class ReplacingStringTokenizer {
    public static void main(String[] args) {
        String input = "But I'm not dead yet! I feel happy!";
        StringTokenizer stringTokenizer = new StringTokenizer(input);
        while (stringTokenizer.hasMoreElements()) {
            System.out.print(stringTokenizer.nextToken() + " ");
        }
        System.out.println();
        System.out.println(Arrays.toString(input.split(" ")));
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            System.out.print(scanner.next() + " ");
        }
    }
}
