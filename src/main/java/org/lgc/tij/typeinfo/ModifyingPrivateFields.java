package org.lgc.tij.typeinfo;

import java.lang.reflect.Field;

/**
 * 反射修改private域
 * final域实际上在遭遇修改时是安全的。运行时系统会在不抛出异常的情况下接受任何修改尝试，但是实际上不会发生任何改变
 * Created by laigc on 2017/1/2.
 */
class WithPrivateFinalField {
    private int i = 1;
    private final String s = "I'm totally safe";
    private String s2 = "Am I safe?";

    @Override
    public String toString() {
        return "WithPrivateFinalField{" +
                "i=" + i +
                ", s='" + s + '\'' +
                ", s2='" + s2 + '\'' +
                '}';
    }
}

public class ModifyingPrivateFields {
    public static void main(String[] args) throws Exception {
        WithPrivateFinalField pf = new WithPrivateFinalField();
        System.out.println(pf);
        Field field = pf.getClass().getDeclaredField("i");
        field.setAccessible(true);
        System.out.println("field.getInt(pf): " + field.getInt(pf));
        field.setInt(pf, 47);
        System.out.println(pf);
        field = pf.getClass().getDeclaredField("s");
        field.setAccessible(true);
        System.out.println("field.get(pf): " + field.get(pf));
        field.set(pf, "No, you're not!"); // final域不会被修改
        System.out.println(pf);
        field = pf.getClass().getDeclaredField("s2");
        field.setAccessible(true);
        System.out.println("field.get(pf): " + field.get(pf));
        field.set(pf, "No, you're not!");
        System.out.println(pf);
    }
}
