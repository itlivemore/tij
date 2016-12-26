package org.lgc.tij.exceptions;

/**
 * 对于在构造阶段可能会抛出异常，并且要求清理的类，最安全的使用方式使用嵌套的try子句
 * Created by laigc on 2016/12/25.
 */
public class CleanUp {
    public static void main(String[] args) {
        try {
            InputFile in = new InputFile("CleanUp.java");
            try {
                // 进入了这里表示构造成功了，需要清理,所以后面用了finally
                String s;
                while ((s = in.getLine()) != null) {

                }
            } catch (Exception e) {
                System.out.println("Caught exception in main");
                e.printStackTrace(System.out);
            } finally {
                in.dispose();
            }
        } catch (Exception e) {
            // 如果构造失败，不用清理
            System.out.println("InputFile construction failed");
        }
    }
}
