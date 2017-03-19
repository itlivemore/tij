package org.lgc.tij.concurrency;

/**
 * 生成int的类
 * Created by laigc on 2017/3/18.
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    // 允许撤销
    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
