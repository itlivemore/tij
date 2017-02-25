package org.lgc.tij.io;

import java.io.*;

/**
 * 输出到文件，并增加行号
 * Created by laigc on 2017/2/25.
 */
public class BasicFileOut {
    public static void main(String[] args) {
        try {
            File file = new File(new File("").getAbsolutePath() + "/pom.xml");
            String s = BufferedInputFile.read(file.getAbsolutePath());
            StringReader stringReader = new StringReader(s);
            BufferedReader bufferedReader = new BufferedReader(stringReader);

            String writeFile = "BasicFileOutput.out";
            FileWriter fileWriter = new FileWriter(writeFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
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
