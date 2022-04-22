package com.siegetd.game.singletons;

import java.util.HashMap;

public class ScoreHandler {
    private static ScoreHandler scoreInstance = null;
    private int health;

    private ScoreHandler(){
        health = 30;
    }

    public static ScoreHandler getInstance(){
        if(scoreInstance == null){
            scoreInstance = new ScoreHandler();
        }
        return scoreInstance;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getHealth(){
        return this.health;
    }
}
