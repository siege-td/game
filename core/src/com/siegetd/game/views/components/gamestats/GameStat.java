package com.siegetd.game.views.components.gamestats;

public class GameStat {

    private String name;
    private int hitpoints;
    private int currency;

    public GameStat(String name, int hitpoints, int currency) {
        this.name = name;
        this.hitpoints = hitpoints;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }
}
