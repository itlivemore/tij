package org.lgc.tij.exceptions;

/**
 * 使用一个需要清理的对象的基本规则：在创建需要清理的对象之后，立即进入一个try-finally语句块
 * Created by laigc on 2016/12/25.
 */
class NeedsCleanup {
    private static long counter = 1;
    private final long id = counter++;

    public void dispose() {
        System.out.println("NeedsCleanup " + id + "disposed");
    }
}

class ConstructionException extends Exception {
}

class NeedsCleanup2 extends NeedsCleanup {
    // 有可能会构造失败
    public NeedsCleanup2() throws ConstructionException {
    }
}

public class CleanupIdiom {
    public static void main(String[] args) {
        // 在需要清理的对象之后紧跟try-finally
        // 如果对象不能构造失败，就不需要catch
        NeedsCleanup nc1 = new NeedsCleanup();
        try {
        } finally {
            nc1.dispose();
        }

        // 可以将不能构造失败的对象群组到一起
        NeedsCleanup nc2 = new NeedsCleanup();
        NeedsCleanup nc3 = new NeedsCleanup();
        try {
        } finally {
            // 按创建对象的逆序清理对象
            nc3.dispose();
            nc2.dispose();
        }

        // 对于每一个可能构造失败的对象后紧跟一个try-finally语句
        try {
            NeedsCleanup2 nc4 = new NeedsCleanup2();
            try {
                NeedsCleanup2 nc5 = new NeedsCleanup2();
                try {
                } finally {
                    nc5.dispose();
                }
            } finally {
                nc4.dispose();
            }
        } catch (ConstructionException e) {
            System.out.println(e);
        }
    }
}
