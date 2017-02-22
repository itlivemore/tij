package org.lgc.tij.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * 在参数中使用匿名内部类缩减程序
 * Created by lgc on 17-2-22.
 */
public class DirList3 {
    public static void main(String[] args) {
        File path = new File("./src/main/java/org/lgc/tij/generics");
        String[] list;
        String regex = ".*\\.java";
        list = path.list(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
        if (list == null || list.length == 0) {
            return;
        }

        for (String item : list) {
            System.out.println(item);
        }
    }
}
