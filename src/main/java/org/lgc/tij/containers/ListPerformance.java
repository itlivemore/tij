package org.lgc.tij.containers;

import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;

import java.util.*;

/**
 * 测试List的性能
 * Created by laigc on 2017/2/19.
 */
public class ListPerformance {
    static Random random = new Random();

    static int reps = 1000;

    static List<Test<List<Integer>>> tests = new ArrayList<>();
    static List<Test<LinkedList<Integer>>> qTests = new ArrayList<>();

    static {
        tests.add(new Test<List<Integer>>("add") {
            @Override
            int test(List<Integer> list, TestParam testParam) {
                int loops = testParam.loops;
                int listSize = testParam.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < listSize; j++) {
                        list.add(j);
                    }
                }
                return loops * listSize;
            }
        });

        tests.add(new Test<List<Integer>>("get") {
            @Override
            int test(List<Integer> list, TestParam testParam) {
                int loops = testParam.loops * reps;
                int listSize = list.size();
                for (int i = 0; i < loops; i++) {
                    list.get(random.nextInt(listSize));
                }
                return loops;
            }
        });

        tests.add(new Test<List<Integer>>("set") {
            @Override
            int test(List<Integer> list, TestParam testParam) {
                int loops = testParam.loops * reps;
                int listSize = list.size();
                for (int i = 0; i < loops; i++) {
                    list.set(random.nextInt(listSize), 47);
                }
                return loops;
            }
        });

        tests.add(new Test<List<Integer>>("iteradd") {
            @Override
            int test(List<Integer> list, TestParam testParam) {
                final int LOOPS = 1000000;
                int half = list.size() / 2;
                ListIterator<Integer> iterator = list.listIterator(half);
                for (int i = 0; i < LOOPS; i++) {
                    iterator.add(47);
                }
                return LOOPS;
            }
        });

        tests.add(new Test<List<Integer>>("insert") {
            @Override
            int test(List<Integer> list, TestParam testParam) {
                int loops = testParam.loops;
                for (int i = 0; i < loops; i++) {
                    list.add(5, 47);
                }
                return loops;
            }
        });

        tests.add(new Test<List<Integer>>("remove") {
            @Override
            int test(List<Integer> list, TestParam testParam) {
                int loops = testParam.loops;
                int size = testParam.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while (list.size() > 5) {
                        list.remove(5);
                    }
                }
                return loops * size;
            }
        });

        // Tests for queue behavior:
        qTests.add(new Test<LinkedList<Integer>>("addFirst") {
            int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addFirst(47);
                }
                return loops * size;
            }
        });
        qTests.add(new Test<LinkedList<Integer>>("addLast") {
            int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addLast(47);
                }
                return loops * size;
            }
        });
        qTests.add(
                new Test<LinkedList<Integer>>("rmFirst") {
                    int test(LinkedList<Integer> list, TestParam tp) {
                        int loops = tp.loops;
                        int size = tp.size;
                        for (int i = 0; i < loops; i++) {
                            list.clear();
                            list.addAll(new CountingIntegerList(size));
                            while (list.size() > 0)
                                list.removeFirst();
                        }
                        return loops * size;
                    }
                });
        qTests.add(new Test<LinkedList<Integer>>("rmLast") {
            int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while (list.size() > 0)
                        list.removeLast();
                }
                return loops * size;
            }
        });
    }

    static class ListTester extends Tester<List<Integer>> {
        public ListTester(List<Integer> container,
                          List<Test<List<Integer>>> tests) {
            super(container, tests);
        }

        // Fill to the appropriate size before each test:
        @Override
        protected List<Integer> initialize(int size) {
            container.clear();
            container.addAll(new CountingIntegerList(size));
            return container;
        }

        // Convenience method:
        public static void run(List<Integer> list,
                               List<Test<List<Integer>>> tests) {
            new ListTester(list, tests).timedTest();
        }
    }

    public static void main(String[] args) {
        Tester.defaultParams = TestParam.array(500);
        Tester<List<Integer>> arrayTest = new Tester<List<Integer>>(null, tests.subList(1, 3)) {
            @Override
            protected List<Integer> initialize(int size) {
                Integer[] array = Generated.array(Integer.class, new CountingGenerator.Integer(), size);
                return Arrays.asList(array);
            }
        };

        arrayTest.setHeadline("Array as List");
        arrayTest.timedTest();
        Tester.defaultParams = TestParam.array(
                10, 5000, 100, 5000, 1000, 1000, 10000, 200);
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        ListTester.run(new ArrayList<Integer>(), tests);
        ListTester.run(new LinkedList<Integer>(), tests);
        ListTester.run(new Vector<Integer>(), tests);
        Tester.fieldWidth = 12;
        Tester<LinkedList<Integer>> qTest =
                new Tester<LinkedList<Integer>>(
                        new LinkedList<Integer>(), qTests);
        qTest.setHeadline("Queue tests");
        qTest.timedTest();
    }
}
