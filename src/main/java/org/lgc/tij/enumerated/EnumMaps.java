package org.lgc.tij.enumerated;

import java.util.EnumMap;
import java.util.Map;

/**
 * EnumMap使用
 * EnumMap要求key要来自一个enum
 * Created by laigc on 2017/3/5.
 */
interface Command {
    void action();
}

public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> enumMap = new EnumMap<>(AlarmPoints.class);
        enumMap.put(AlarmPoints.KITCHEN, new Command() {
            @Override
            public void action() {
                System.out.println("kitchen fire!");
            }
        });

        enumMap.put(AlarmPoints.BATHROOM, new Command() {
            @Override
            public void action() {
                System.out.println("Bathroom alert");
            }
        });

        for (Map.Entry<AlarmPoints, Command> entry : enumMap.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            entry.getValue().action();
        }

        // 如果没有存入，返回null
        Command command = enumMap.get(AlarmPoints.UTILITY);
        if (command == null) {
            System.out.println("command is null");
        }

    }
}
