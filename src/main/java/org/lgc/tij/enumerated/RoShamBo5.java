package org.lgc.tij.enumerated;

import java.util.EnumMap;

import static org.lgc.tij.enumerated.Outcome.DRAW;
import static org.lgc.tij.enumerated.Outcome.LOSE;
import static org.lgc.tij.enumerated.Outcome.WIN;

/**
 * 使用EnumMap分发
 * Created by laigc on 2017/3/5.
 */
public enum RoShamBo5 implements Competitor<RoShamBo5> {
    PAPER, SCISSORS, ROCK;

    static EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>> table = new EnumMap<>(RoShamBo5.class);

    static {
        for (RoShamBo5 it : RoShamBo5.values()) {
            table.put(it, new EnumMap<>(RoShamBo5.class));
        }
        initRow(PAPER, DRAW, LOSE, WIN);
        initRow(SCISSORS, WIN, DRAW, LOSE);
        initRow(ROCK, LOSE, WIN, DRAW);
    }

    static void initRow(RoShamBo5 it, Outcome vPAPER, Outcome vSCISSORS, Outcome vROCK) {
        EnumMap<RoShamBo5, Outcome> row = RoShamBo5.table.get(it);
        row.put(RoShamBo5.PAPER, vPAPER);
        row.put(RoShamBo5.SCISSORS, vSCISSORS);
        row.put(RoShamBo5.ROCK, vROCK);
    }

    @Override
    public Outcome compete(RoShamBo5 competer) {
        // 这里进行了两次分发
        return table.get(this).get(competer);
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo5.class, 20);
    }
}
