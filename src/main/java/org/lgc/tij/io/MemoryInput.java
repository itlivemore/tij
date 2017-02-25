package org.lgc.tij.io;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * StringReader
 * Created by laigc on 2017/2/25.
 */
public class MemoryInput {
    public static void main(String[] args) {
        try {
            File file = new File(new File("").getAbsolutePath() + "/pom.xml");
            StringReader stringReader = new StringReader(BufferedInputFile.read(file.getAbsolutePath()));
            int c;
            while ((c = stringReader.read()) != -1) {
                System.out.println((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
