package org.lgc.tij.typeinfo;

import net.mindview.util.Null;

import java.util.List;

/**
 * Robot接口，定义了一个名字，一个模型，一个描述Robot行为能力的List<Operation>
 * Created by laigc on 2017/1/1.
 */
public interface Robot {
    String name();

    String model();

    List<Operation> operations();

    class Test {
        public static void test(Robot robot) {
            if (robot instanceof Null) {
                System.out.println("[Null Robot]");
            }
            System.out.println("Robot name: " + robot.name());
            System.out.println("Robot model: " + robot.model());
            for (Operation operation : robot.operations()) {
                System.out.println(operation.description());
                operation.command();
            }
        }
    }
}
