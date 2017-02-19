package org.lgc.tij.containers;

/**
 * 土拨鼠类
 * Created by laigc on 2017/2/18.
 */
public class Groundhog {
    protected int number;

    public Groundhog(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Groundhog #" + number;
    }
}
