package org.lgc.tij.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 特殊方法transferTo()和transferFrom()允许将一个通道与另一个通道直接相连
 * Created by laigc on 2017/2/26.
 */
public class TransferTo {
    public static void main(String[] args) throws IOException {
        String file1 = "data.txt";
        String file2 = "data2.txt";

        FileChannel in = new FileInputStream(file1).getChannel();
        FileChannel out = new FileOutputStream(file2).getChannel();

        in.transferTo(0, in.size(), out);
        // 或者
//        out.transferFrom(in, 0, in.size());
    }
}
