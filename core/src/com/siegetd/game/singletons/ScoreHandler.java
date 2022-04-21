package com.siegetd.game.singletons;

import java.util.HashMap;

public class ScoreHandler {
    private static ScoreHandler scoreInstance = null;
    private int health = 0;

    private ScoreHandler(){

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
