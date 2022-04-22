package com.siegetd.game.views.components.gamestats;

import com.siegetd.game.singletons.Currency;

public class GameStat {

    private String name;
    private int hitpoints = 10;
    Currency currency = Currency.getInstance();

    public GameStat(String name, int hitpoints, int currency) {
        this.name = name;
        this.hitpoints = hitpoints;
        //this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public int getCurrency() {
        return currency.getCurrency();
    }

}
