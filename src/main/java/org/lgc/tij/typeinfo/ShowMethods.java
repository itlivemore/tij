package org.lgc.tij.typeinfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类方法提取
 * Created by laigc on 2017/1/1.
 */
public class ShowMethods {
    private static Pattern pattern = Pattern.compile("\\w+\\.");

    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("org.lgc.tij.typeinfo.ShowMethods");
            Method[] methods = c.getMethods();
            Constructor<?>[] constructors = c.getConstructors();
            for (Method method : methods) {
                Matcher matcher = pattern.matcher(method.toString());
                System.out.println(matcher.replaceAll(""));
            }
            for (Constructor<?> constructor : constructors) {
                Matcher matcher = pattern.matcher(constructor.toString());
                System.out.println(matcher.replaceAll(""));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
