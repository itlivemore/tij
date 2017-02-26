package org.lgc.tij.io;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * Charset提供了把数据编码成多种不同类型的字符集的工具
 * 本示例打印了这些编码字符集
 * Created by laigc on 2017/2/26.
 */
public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        Iterator<String> iterator = charsets.keySet().iterator();
        while (iterator.hasNext()) {
            String csName = iterator.next();
            System.out.print(csName);
            Iterator<String> aliases = charsets.get(csName).aliases().iterator();
            if (aliases.hasNext()) {
                System.out.print(":");
            }
            while (aliases.hasNext()) {
                System.out.print(aliases.next());
                if (aliases.hasNext()) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }
}
