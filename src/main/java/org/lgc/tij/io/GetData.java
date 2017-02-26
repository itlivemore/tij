package org.lgc.tij.io;

import java.nio.ByteBuffer;

/**
 * ByteBuffer获取基本类型
 * Created by laigc on 2017/2/26.
 */
public class GetData {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);

        int i = 0;
        // 检测缓冲器里的内容都是0
        while (i++ < buffer.limit()) {
            if (buffer.get() != 0) {
                System.out.println("nonzero");
            }
        }
        System.out.println("i = " + i);

        // char的存储与读取
        buffer.rewind();
        buffer.asCharBuffer().put("Howdy!");
        char c;
        while ((c = buffer.getChar()) != 0) {
            System.out.print(c + " ");
        }
        System.out.println();

        // shor的存储与读取
        buffer.rewind();
        buffer.asShortBuffer().put((short) 471142);
        System.out.println(buffer.getShort());

        // int的存储与读取
        buffer.rewind();
        buffer.asIntBuffer().put(99471142);
        System.out.println(buffer.getInt());

        // long的存储与读取
        buffer.rewind();
        buffer.asLongBuffer().put(99471142);
        System.out.println(buffer.getLong());

        // float的存储与读取
        buffer.rewind();
        buffer.asFloatBuffer().put(99471142);
        System.out.println(buffer.getFloat());

        // double存储与读取
        buffer.rewind();
        buffer.asDoubleBuffer().put(99471142);
        System.out.println(buffer.getDouble());
    }
}
