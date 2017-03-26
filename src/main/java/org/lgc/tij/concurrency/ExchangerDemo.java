package org.lgc.tij.concurrency;

import net.mindview.util.BasicGenerator;
import net.mindview.util.Generator;

import java.util.List;
import java.util.concurrent.*;

/**
 * Exchanger是在两个任务之前交换对象的栅栏。
 * 当这些任务进入栅栏时，它们各自拥有一个对象，当它们离开时，它们都拥有之前由对象持有的对象。
 * Exchanger的典型应用场景是：一个任务在创建对象，这些对象的生产代价很高昂，而另一个任务在消费这些对象。
 * 通过这种方式，可以有更多的对象在被创建的同时被消费。
 * 本例中，当调用Exchanger.exchanger()方法时，它将阻塞直到对方任务调用它自己的exchange()方法，
 * 那时，这两个exchange()方法将全部完成，而List<T>则被互换.
 * 因为有了Exchanger，填充一个列表和消费另一个列表便可以同时发生了。
 * <p>
 * Created by laigc on 2017/3/26.
 */

// 生产者
class ExchangerProducer<T> implements Runnable {
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;

    public ExchangerProducer(Generator<T> generator, Exchanger<List<T>> exchanger, List<T> holder) {
        this.generator = generator;
        this.exchanger = exchanger;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (int i = 0; i < ExchangerDemo.size; i++) {
                    holder.add(generator.next());
                }
                holder = exchanger.exchange(holder);
            }
        } catch (InterruptedException e) {

        }
    }
}

// 消费者
class ExchangerConsumer<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;

    public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder) {
        this.exchanger = exchanger;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                holder = exchanger.exchange(holder);
                for (T t : holder) {
                    value = t;
                    holder.remove(t);
                }
            }
        } catch (InterruptedException e) {

        }
        System.out.println("Final value: " + value);
    }
}

public class ExchangerDemo {
    public static int size = 10;
    static int delay = 5;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> exchanger = new Exchanger<>();
        // 因为需要在遍历时调用remove()，所以这里用CopyOnWriteArrayList
        List<Fat> producerList = new CopyOnWriteArrayList<>();
        List<Fat> consumerList = new CopyOnWriteArrayList<>();
        threadPool.execute(new ExchangerProducer<>(BasicGenerator.create(Fat.class), exchanger, producerList));
        threadPool.execute(new ExchangerConsumer<>(exchanger, consumerList));
        TimeUnit.SECONDS.sleep(delay);
        threadPool.shutdownNow();
    }
}
