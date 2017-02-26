package org.lgc.tij.io;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * 通过字节存放模式设置来改变字符中的字节次序
 * 字节存放模式有：高位优先（优先读取高位）和低位优先（优先读取低位）
 * Created by laigc on 2017/2/26.
 */
public class Endians {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[12]);
        byteBuffer.asCharBuffer().put("abcdef");
        // 默认是高位优先的
        System.out.println(Arrays.toString(byteBuffer.array()));

        byteBuffer.rewind();
        // 设置成高位优先，a被读取为0,97
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        byteBuffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(byteBuffer.array()));

        byteBuffer.rewind();
        // 设置成低位优先，a被读取为97,0
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(byteBuffer.array()));
    }
}
