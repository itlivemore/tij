package org.lgc.tij.typeinfo;

import java.util.HashMap;

/**
 * 递归计数
 * Created by laigc on 2017/1/1.
 */
public class TypeCounter extends HashMap<Class<?>, Integer> {
    private Class<?> baseType;

    public TypeCounter(Class<?> baseType) {
        this.baseType = baseType;
    }

    public void count(Object obj) {
        Class<?> type = obj.getClass();
        if (!baseType.isAssignableFrom(type)) {
            throw new RuntimeException(obj + "incorrect type:" + type + ", should be " +
                    "type or subtype of " + baseType);
        }
        countClass(type);
    }

    private void countClass(Class<?> type) {
        Integer quantity = get(type);
        put(type, quantity == null ? 1 : quantity + 1);
        Class<?> superclass = type.getSuperclass();
        if (superclass != null && baseType.isAssignableFrom(superclass)) {
            countClass(superclass); // 递归处理父类
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Entry<Class<?>, Integer> entry : entrySet()) {
            sb.append(entry.getKey().getSimpleName());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");
        return  sb.toString();
    }
}
