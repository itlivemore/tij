package org.lgc.tij.io;

import java.io.*;

/**
 * 简化输出到文件代码,主要是用new PrintWriter(file)
 * Created by laigc on 2017/2/25.
 */
public class FileOutputShortcut {
    public static void main(String[] args) {
        try {
            File file = new File(new File("").getAbsolutePath() + "/pom.xml");
            String s = BufferedInputFile.read(file.getAbsolutePath());
            StringReader stringReader = new StringReader(s);
            BufferedReader bufferedReader = new BufferedReader(stringReader);

            String writeFile = "BasicFileOutput.out";
            PrintWriter printWriter = new PrintWriter(writeFile);
            int lineCount = 1;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(lineCount++ + ":" + line);
            }
            printWriter.close();
            System.out.println(BufferedInputFile.read(writeFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
