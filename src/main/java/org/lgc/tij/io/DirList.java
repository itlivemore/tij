package org.lgc.tij.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 目录列表及过滤
 * Created by lgc on 17-2-20.
 */
public class DirList {
    public static void main(String[] args) {
        File path = new File("./src/main/java/org/lgc/tij/generics");
        System.out.println(path.getAbsolutePath());
        String[] list;

        list = path.list();
        if (list == null || list.length == 0) {
            return;
        }
        for (String dirItem : list) {
            System.out.println(dirItem);
        }

        System.out.println("------------------------");

        list = path.list(new DirFilter(".*\\.java"));

        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }

    static class DirFilter implements FilenameFilter {
        private Pattern pattern;

        public DirFilter(String regex) {
            pattern = Pattern.compile(regex);
        }

        @Override
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }

}
