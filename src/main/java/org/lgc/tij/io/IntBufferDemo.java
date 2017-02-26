package org.lgc.tij.io;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * 通过IntBuffer操纵ByteBuffer中的int型数据
 * Created by laigc on 2017/2/26.
 */
public class IntBufferDemo {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(BSIZE);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();

        // 存储int数组
        intBuffer.put(new int[]{11, 42, 47, 99, 143, 811, 1016});
        // 按下标获取
        System.out.println(intBuffer.get(3));
        // 按下标设置
        intBuffer.put(3, 1811);
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            int i = intBuffer.get();
            System.out.println(i);
        }
    }
}
