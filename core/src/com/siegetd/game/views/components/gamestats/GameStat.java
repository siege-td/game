package com.siegetd.game.views.components.gamestats;

import com.siegetd.game.singletons.Currency;
import com.siegetd.game.singletons.ScoreHandler;

public class GameStat {

    private String name;
    Currency currency = Currency.getInstance();
    ScoreHandler health = ScoreHandler.getInstance();

    public GameStat(String name, int hitpoints, int currency) {
        this.name = name;
        //this.hitpoints = hitpoints;
        //this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public int getHitpoints() {
        return health.getHealth();
    }

    public int getCurrency() {
        return currency.getCurrency();
    }

}
