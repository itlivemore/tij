package org.lgc.tij.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * 本示例用一个很简单的算法（交换相邻字符），以对CharBuffer中的字符进行编码(scramble)和译码(unscramble)
 * Created by laigc on 2017/2/26.
 */
public class UsingBuffers {
    private static void symmetricScramble(CharBuffer charBuffer) {
        charBuffer.mark(); // 将mark指向position
        char c1 = charBuffer.get(); // get会使指针psoition下移
        char c2 = charBuffer.get();
        charBuffer.reset();// 将position恢复到mark
        charBuffer.put(c2).put(c1); // put会使指针position下移
    }

    public static void main(String[] args) {
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length * 2);
        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        charBuffer.put(data);
        System.out.println(charBuffer.rewind());
        symmetricScramble(charBuffer);
        System.out.println(charBuffer.rewind());
        symmetricScramble(charBuffer);
        System.out.println(charBuffer.rewind());
    }
}
