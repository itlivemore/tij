package org.lgc.tij.annotations;

/**
 * 非空注解
 * 注解嵌套注解修改默认值
 * Created by laigc on 2017/3/5.
 */
public @interface Uniqueness {
    // 修改了默认值unique
    Contraints contraints() default @Contraints(unique = true);
}
