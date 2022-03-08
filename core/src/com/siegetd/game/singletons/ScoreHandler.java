package com.siegetd.game.singletons;

import java.util.HashMap;

public class ScoreHandler {
    private static ScoreHandler scoreInstance = null;
    private HashMap<String,Integer> playerScores;

    public ScoreHandler(){
    }

    public static ScoreHandler getInstance(){
        if(scoreInstance == null){
            scoreInstance = new ScoreHandler();
        }
        return scoreInstance;
    }

    private HashMap<String, Integer> getPlayerScores() {
        return playerScores;
    }

    private int getScoreByID(String playerId){
        return playerScores.get(playerId);
    }

    private void subtractScore(String playerId, int amount){
        playerScores.put(playerId,playerScores.get(playerId) - amount);
    }

}
