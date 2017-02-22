package org.lgc.tij.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * 使用匿名内部类
 * Created by lgc on 17-2-22.
 */
public class DirList2 {
    public static void main(String[] args) {
        File path = new File("./src/main/java/org/lgc/tij/generics");
        String[] list;
        list = path.list(filter(".*\\.java"));
        if (list == null || list.length == 0) {
            return;
        }
        for (String item : list) {
            System.out.println(item);
        }
    }

    public static FilenameFilter filter(final String regex) {
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }
}
