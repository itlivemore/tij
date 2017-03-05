package org.lgc.tij.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段约束
 * Created by laigc on 2017/3/5.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Contraints {
    boolean primaryKey() default false;

    boolean allowNull() default true;

    boolean unique() default false;
}
