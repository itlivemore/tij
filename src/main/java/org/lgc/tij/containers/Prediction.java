package org.lgc.tij.containers;

import java.util.Random;

/**
 * 预报类
 * Created by laigc on 2017/2/18.
 */
public class Prediction {
    private static Random random = new Random(47);
    private boolean shadow = random.nextDouble() > 0.5;

    @Override
    public String toString() {
        if (shadow) {
            return "Six more weeks of Winter!";
        } else {
            return "Early Spring";
        }
    }
}
