package org.lgc.tij.enumerated;

import java.util.EnumSet;

import static net.mindview.util.Print.print;

/**
 * 关于洗车的例子
 * 每个客户在洗车时，都有一个选择菜单，每个选择对应一个动作。
 * 可以将一个常量相关的方法关联到一个选择上，再使用一个EnumSet来保存客户的选择
 * Created by laigc on 2017/3/5.
 */
public class CarWash {
    public enum Cycle {
        UNDERBODY {
            void action() {
                print("Spraying the underbody");
            }
        },
        WHEELWASH {
            void action() {
                print("Washing the wheels");
            }
        },
        PREWASH {
            void action() {
                print("Loosening the dirt");
            }
        },
        BASIC {
            void action() {
                print("The basic wash");
            }
        },
        HOTWAX {
            void action() {
                print("Applying hot wax");
            }
        },
        RINSE {
            void action() {
                print("Rinsing");
            }
        },
        BLOWDRY {
            void action() {
                print("Blowing dry");
            }
        };

        abstract void action();
    }

    EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE);

    public void add(Cycle cycle) {
        cycles.add(cycle);
    }

    public void washCar() {
        for (Cycle cycle : cycles) {
            cycle.action();
        }
    }

    @Override
    public String toString() {
        return cycles.toString();
    }

    public static void main(String[] args) {
        CarWash wash = new CarWash();
        System.out.println(wash);

        wash.add(Cycle.BLOWDRY);
        wash.add(Cycle.BLOWDRY); // 重复的会被忽略
        wash.add(Cycle.RINSE);
        wash.add(Cycle.HOTWAX);
        // 顺序取决于enum中的顺序，而不是添加的顺序
        wash.washCar();
        System.out.println(wash);
    }
}
