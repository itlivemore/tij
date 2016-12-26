package org.lgc.tij.exceptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 在构造器中使用try catch
 * Created by laigc on 2016/12/25.
 */
public class InputFile {
    private BufferedReader in;

    public InputFile(String fname) throws Exception {
        try {
            in = new BufferedReader(new FileReader(fname));
        } catch (FileNotFoundException e) {
            // 如果是报FileNotFoundException，表示文件还没有打开，所有不需要执行in.close()
            System.out.println("Could not open " + fname);
            throw e;
        } catch (Exception e) {
            // 捕获到其他异常，文件已经打开了，需要执行in.close()
            try {
                in.close();
            } catch (IOException e1) {
                System.out.println("in.close() unsuccessful");
            }
            throw e;
        } finally {
        }
    }

    public String getLine() {
        String s = null;
        try {
            s = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException("readLine() failed");
        }
        return s;
    }

    public void dispose() {
        try {
            in.close();
            System.out.println("dispose() successful");
        } catch (IOException e) {
            throw new RuntimeException("in.close() failed");
        }
    }
}
