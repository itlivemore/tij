package org.lgc.tij.io;

/** 进程自身产生的异常
 * Created by laigc on 2017/2/25.
 */
public class OSExecuteException extends RuntimeException {
    public OSExecuteException(String why) {
        super(why);
    }
}
