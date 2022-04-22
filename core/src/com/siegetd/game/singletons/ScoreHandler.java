package com.siegetd.game.singletons;

import com.siegetd.game.EngineState;
import com.siegetd.game.api.SocketConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;

public class ScoreHandler {
    private static ScoreHandler scoreInstance = null;

    private int health;
    private int currency;

    private ScoreHandler(){
        health = 30;
        currency = 100;
    }

    public static ScoreHandler getInstance(){
        if(scoreInstance == null){
            scoreInstance = new ScoreHandler();
        }
        return scoreInstance;
    }

    public void addCurrency(int amount){
        currency += amount;

        updateGameDataOnServer();
    }

    public void subtractCurrency(int amount){
        currency -= amount;

        updateGameDataOnServer();
    }

    public int getCurrency() {
        return currency;
    }

    public void setHealth(int health){
        this.health = health;

        updateGameDataOnServer();
    }

    public int getHealth(){
        return this.health;
    }

    private void updateGameDataOnServer() {
        try {
            JSONObject object = new JSONObject();
            object.put("pin", EngineState.pin);
            object.put("playerName", SocketConnection.getInstance().getSocket().id().substring(0, 5));
            object.put("hitpoints", health);
            object.put("currency", currency);

            SocketConnection.getInstance().getSocket().emit("update_game_data", object);
        } catch (JSONException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
