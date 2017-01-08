import org.lgc.tij.generics.Holder;

/**
 * 编译器什么时候关注原生类型和无界通配符的差异
 * Created by laigc on 2017/1/8.
 */
public class Wildcards {
    static void rawArgs(Holder holder, Object arg) {
        /*Holder使用原生类型，可以将任何类型的对象传递给setValue,而这个对象将被向上转型为Object*/
        holder.setValue(arg); // Warning
        holder.setValue(new Wildcards());// Warning
//        T t = holder.getValue();//没有T
        Object value = holder.getValue();
    }

    // 同rawArgx()，但是错误而不是警告
    static void unboundedArg(Holder<?> holder, Object arg) {
        /*这里的Holder<?>将持有某种具体类型，因此将不能向其中传递Object*/
//        holder.setValue(arg);
//        holder.setValue(new Wildcards());
//        T t = holder.getValue();
        Object value = holder.getValue();
    }

    static <T> T exact1(Holder<T> holder) {
        T t = holder.getValue();
        return t;
    }

    static <T> T exact2(Holder<T> holder, T arg) {
        holder.setValue(arg);
        T t = holder.getValue();
        return t;
    }

    static <T> T wildSubtype(Holder<? extends T> holder, T arg) {
//        holder.setValue(arg);
        T t = holder.getValue();
        return t;
    }

    static <T> void wildSupertype(Holder<? super T> holder, T arg) {
        holder.setValue(arg);
//        T t = holder.getValue();
        Object value = holder.getValue();
    }

    public static void main(String[] args) {
        Holder raw = new Holder<Long>();
        raw = new Holder();

        Holder<Long> qualified = new Holder<>();
        Holder<?> unbounded = new Holder<Long>();
        Holder<? extends Long> bounded = new Holder<Long>();
        Long lng = 1L;

        rawArgs(raw, lng);
        rawArgs(qualified, lng);
        rawArgs(unbounded, lng);
        rawArgs(bounded, lng);

        unboundedArg(unbounded, lng);
        unboundedArg(qualified, lng);
        unboundedArg(unbounded, lng);
        unboundedArg(bounded, lng);

        Object r1 = exact1(raw);
        Long r2 = exact1(qualified);
        Object r3 = exact1(unbounded);
        Long r4 = exact1(bounded);

        Long r5 = exact2(raw, lng);
        Long r6 = exact2(qualified, lng);
//        Long r7 = exact2(unbounded, lng);
//        Long r8 = exact2(bounded, lng);

        Long r9 = wildSubtype(raw, lng);
        Long r10 = wildSubtype(qualified, lng);
        Object r11 = wildSubtype(unbounded, lng);
        Long r12 = wildSubtype(bounded, lng);


        wildSupertype(raw, lng);
        wildSupertype(qualified, lng);
//        wildSupertype(unbounded, lng);
//        wildSupertype(bounded, lng);

    }
}
