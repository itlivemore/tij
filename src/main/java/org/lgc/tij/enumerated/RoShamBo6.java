package org.lgc.tij.enumerated;

import static org.lgc.tij.enumerated.Outcome.DRAW;
import static org.lgc.tij.enumerated.Outcome.LOSE;
import static org.lgc.tij.enumerated.Outcome.WIN;

/**
 * 使用二维数据来判断结果
 * Created by laigc on 2017/3/5.
 */
public enum RoShamBo6 implements Competitor<RoShamBo6> {
    PAPER, SCISSORS, ROCK;

    private static Outcome[][] table = {
            {DRAW, LOSE, WIN}, // PAPER
            {WIN, DRAW, LOSE}, // SCISSORS
            {LOSE, WIN, DRAW}}; // ROCK;


    @Override
    public Outcome compete(RoShamBo6 competer) {
        return table[this.ordinal()][competer.ordinal()];
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo6.class, 20);
    }
}
