package org.lgc.tij.containers;

import java.lang.ref.*;
import java.util.LinkedList;

/**
 * 持有引用
 * Created by laigc on 2017/2/19.
 */
class VeryBig {
    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String ident;

    public VeryBig(String ident) {
        this.ident = ident;
    }

    @Override
    public String toString() {
        return ident;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing " + ident);
    }
}

public class References {
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<>();

    public static void checkQueue() {
        Reference<? extends VeryBig> reference = rq.poll();
        if (reference != null) {
            System.out.println("In queue " + reference.get());
        }
    }

    public static void main(String[] args) {
        int size = 10;

        LinkedList<SoftReference<VeryBig>> softReferenceLinkedList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            softReferenceLinkedList.add(new SoftReference<>(new VeryBig("Soft " + i), rq));
            System.out.println("Just created: " + softReferenceLinkedList.getLast());
            checkQueue();
        }

        LinkedList<WeakReference<VeryBig>> weakReferenceLinkedList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            weakReferenceLinkedList.add(new WeakReference<VeryBig>(new VeryBig("Weak " + i), rq));
            System.out.println("Just created: " + weakReferenceLinkedList.getLast());
            checkQueue();
        }

        SoftReference<VeryBig> softReference = new SoftReference<VeryBig>(new VeryBig("Soft"));
        WeakReference<VeryBig> weakReference = new WeakReference<VeryBig>(new VeryBig("Weak"));
        System.gc();
        LinkedList<PhantomReference<VeryBig>> phantomReferences = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            phantomReferences.add(new PhantomReference<>(new VeryBig("Phantom " + i), rq));
            System.out.println("Justed created: " + phantomReferences.getLast());
            checkQueue();
        }
    }
}
