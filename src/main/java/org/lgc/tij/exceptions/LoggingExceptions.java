package org.lgc.tij.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**java.util.logging.Logger记录异常信息
 * Created by laigc on 2016/12/24.
 */
class LoggingException extends Exception {
    private static Logger logger = Logger.getLogger("LoggingException ");
    public LoggingException() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace)); // 获得异常信息
        logger.severe(trace.toString());//记录异常信息日志
    }
}
public class LoggingExceptions {
    public static void main(String[] args) {
        try {
            throw new LoggingException();
        } catch (LoggingException e) {
            System.out.println("Caught " + e);
        }

        try {
            throw new LoggingException();
        } catch (LoggingException e) {
            System.out.println("Caught " + e);
        }
    }
}
