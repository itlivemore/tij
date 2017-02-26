package org.lgc.tij.io;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * 文件加锁示例
 * Created by laigc on 2017/2/26.
 */
public class FileLocking {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("file.txt");
        FileLock fileLock = fileOutputStream.getChannel().tryLock();// tryLock是非阻塞式的
        if (fileLock != null) {
            System.out.println("Locked File");
            TimeUnit.MILLISECONDS.sleep(100);
            fileLock.release();
            System.out.println("Released Lock");
        }
        fileOutputStream.close();
    }
}
