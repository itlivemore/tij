package org.lgc.tij.enumerated;

import java.util.Random;

/**
 * 自动售货机的状态
 * Created by laigc on 2017/3/5.
 */
public enum Input {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABORT_TRANSACTION {
        public int amount() { // 这个状态没有价格
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP { // This must be the last instance.
        public int amount() { // 这个状态没有价格
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };
    int value; // In cents

    Input(int value) {
        this.value = value;
    }

    Input() {
    }

    int amount() {
        return value;
    }

    ; // In cents
    static Random rand = new Random(47);

    public static Input randomSelection() {
        // Don't include STOP:
        return values()[rand.nextInt(values().length - 1)];
    }
}
