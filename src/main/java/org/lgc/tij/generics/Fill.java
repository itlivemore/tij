package org.lgc.tij.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 在Collection中添加任意对象
 * Created by laigc on 2017/2/11.
 */
public class Fill {
    public static <T> void fill(Collection<T> collection, Class<? extends T> classToken, int size) {
        for (int i = 0; i < size; i++) {
            try {
                collection.add(classToken.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Contract {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getName() + " " + id;
    }
}

class TitleTransfer extends Contract {
}

class FillTest {
    public static void main(String[] args) {
        List<Contract> contracts = new ArrayList<>();
        Fill.fill(contracts, Contract.class, 3);
        Fill.fill(contracts, TitleTransfer.class, 2);
        for (Contract contract : contracts) {
            System.out.println(contract);
        }

        SimpleQueue<Contract> contractsQueue = new SimpleQueue<>();
//        Fill.fill(contractsQueue,Contract.class,3); // 不能用于SimpleQueue,说明这里不够泛化
    }
}
