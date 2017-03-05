package org.lgc.tij.enumerated;

import static org.lgc.tij.enumerated.Outcome.DRAW;
import static org.lgc.tij.enumerated.Outcome.LOSE;
import static org.lgc.tij.enumerated.Outcome.WIN;

/**
 * 石头剪刀布游戏
 * 通过enum存储两个手势比较结果，直接查询输赢
 * Created by laigc on 2017/3/5.
 */
public enum RoShamBo2 implements Competitor<RoShamBo2> {
    PAPER(DRAW, LOSE, WIN),//三个参数分是paper和paper,scissors,rock比较的结果,下面的类似
    SCISSORS(WIN, DRAW, LOSE),
    ROCK(LOSE, WIN, DRAW);
    private Outcome vPAPER, vSCISSORS, vROCK;

    RoShamBo2(Outcome paper, Outcome scissors, Outcome rock) {
        this.vPAPER = paper;
        this.vSCISSORS = scissors;
        this.vROCK = rock;
    }

    public Outcome compete(RoShamBo2 it) {
        switch (it) {
            default:
            case PAPER:
                return vPAPER;
            case SCISSORS:
                return vSCISSORS;
            case ROCK:
                return vROCK;
        }
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo2.class, 20);
    }
}
