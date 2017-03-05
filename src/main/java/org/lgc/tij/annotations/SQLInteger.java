package org.lgc.tij.annotations;

/**
 * SQL的Integer类型
 * Created by laigc on 2017/3/5.
 */
public @interface SQLInteger {
    String name() default "";

    Contraints contraints() default @Contraints;

}
