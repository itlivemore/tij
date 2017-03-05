package org.lgc.tij.enumerated;

/**
 * 向枚举中添加新方法
 * 本例添加构造方法
 * Created by laigc on 2017/3/4.
 */
public enum OzWitch {
    WEST("Miss Gulch, aka the Wicked Witch of the West"),
    NORTH("Glinda, the Good Witch of the North"),
    EAST("Wicked Witch of the East, wearer of the Ruby " +
            "Slippers, crushed by Dorothy's house"),
    SOUTH("Good by inference, but missing"); // 注意要有;号，且新增的方法要在enum实例之后

    private String desciption;

    OzWitch(String desciption) {
        this.desciption = desciption;
    }

    public String getDesciption() {
        return desciption;
    }

    public static void main(String[] args) {
        for (OzWitch ozWitch : OzWitch.values()) {
            System.out.println(ozWitch + " : " + ozWitch.getDesciption());
        }
    }
}
