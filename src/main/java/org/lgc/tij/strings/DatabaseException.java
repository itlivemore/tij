package org.lgc.tij.strings;

/**
 * String.format()，接受与Formatter.formatter()方法一样的参数，但返回一个String对象
 * <p>
 * Created by laigc on 2016/12/30.
 */
public class DatabaseException extends Exception {
    public DatabaseException(int transactionID, int queryID, String message) {
        super(String.format("(t%d ,q%d) %s", transactionID, queryID, message));
    }

    public static void main(String[] args) {
        try {
            throw new DatabaseException(3, 7, "Write failed");
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

}
