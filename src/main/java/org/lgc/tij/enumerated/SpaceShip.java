package org.lgc.tij.enumerated;

/**
 * 覆盖enum的方法
 * 本例覆盖toString()方法
 * Created by laigc on 2017/3/5.
 */
public enum SpaceShip {
    SCOUT, CARGO, TRANSPORT, CRUISER, BATTLESHIP, MOTHERSHIP;


    @Override
    public String toString() {
        String id = name();
        String lower = id.toString().toLowerCase();
        return id.charAt(0) + lower;
    }

    public static void main(String[] args) {
        for (SpaceShip spaceShip : values()) {
            System.out.println(spaceShip);
        }
    }
}
