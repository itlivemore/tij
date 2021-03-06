package org.lgc.tij.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**记录其他人的异常信息
 * Created by laigc on 2016/12/24.
 */
public class LoggingExceptions2 {
    private static Logger logger = Logger.getLogger(LoggingExceptions2.class.getName());

    static void logException(Exception e) { // 记录异常信息
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }

    public static void main(String[] args) {
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            logException(e);
        }
    }
}
