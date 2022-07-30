package com.bowencraft.hexoplate.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Plate {
    // ArrayList<Player, ArrayList<Pos, Plate>> playerplate
    // playerplate.get(Player).get(Polarx+","+Polarz)
    // ArrayList<Player, ArrayList<player>> member

    private final int PolarX;
    private final int PolarZ;

    public int getPolarX() {
        return PolarX;
    }

    public int getPolarZ() {
        return PolarZ;
    }

    private final Player owner;
    // private ArrayList<Player> memberList;

    private String type;

    private int level;



    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addLevel(int level) {
        this.level += level;
    }

    public void reducelevel(int level) {
        this.level -= level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Plate(Player owner, int polarX, int polarZ, String type) {
        this.owner = owner;

        PolarX = polarX;
        PolarZ = polarZ;

        this.type = type;
        level = 0;

    }

    public Plate(Player owner, int polarX, int polarZ, String type, int level) {
        this.owner = owner;

        PolarX = polarX;
        PolarZ = polarZ;

        this.type = type;
        this.level = level;

    }

}
