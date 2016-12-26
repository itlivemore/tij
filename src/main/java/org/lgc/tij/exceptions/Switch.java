package org.lgc.tij.exceptions;

import static net.mindview.util.Print.print;

/**
 * Created by laigc on 2016/12/25.
 */
public class Switch {
    private boolean state = false;

    public boolean read() {
        return state;
    }

    public void on() {
        state = true;
        print(this);
    }

    public void off() {
        state = false;
        print(this);
    }

    @Override
    public String toString() {
        return "Switch{" +
                "state=" + state +
                '}';
    }
}
