package org.lgc.tij.enumerated;

/**
 * 比较接口
 * Created by laigc on 2017/3/5.
 */
public interface Competitor<T extends Competitor> {
    Outcome compete(T competer);
}
