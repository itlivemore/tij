import org.lgc.tij.generics.Holder;

/**
 * 捕获转换
 * Created by laigc on 2017/1/8.
 */
public class CaptureConversion {
    /*f1()中的类型都是确切的，没有通配符或边界。
    * 在f2()中，Holder参数是一个无界通配符，因此它看起来是未知的。
    * 但是在f2()中，f1()被调用，而f1()需要一个已知参数。
    * 这里所发生的是：参数类型在调用f2()的过程中被捕获，因此它可以在f1()的调用中被使用*/
    static <T> void f1(Holder<T> holder) {
        T t = holder.getValue();
        System.out.println(t.getClass().getName());
    }

    static void f2(Holder<?> holder) {
        f1(holder);
    }

    public static void main(String[] args) {
        Holder raw = new Holder<Integer>(1);
        f1(raw);
        f2(raw);

        Holder rawBasic = new Holder();
        rawBasic.setValue(new Object());
        f2(rawBasic);

        Holder<?> wildcarded = new Holder<Double>(1.0);
        f2(wildcarded);
    }
}
