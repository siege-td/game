package com.siegetd.game.views.components.gamestats;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.siegetd.game.EngineState;
import com.siegetd.game.api.SocketConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class GameStats {

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private BitmapFont font;

    private Socket socket;

    private ArrayList<GameStat> gameStatList;
    private GameStat gameStat;

    public GameStats() throws URISyntaxException {
        this.socket = SocketConnection.getInstance().getSocket();

        this.gameStatList = new ArrayList<>();
        this.gameStat = new GameStat("Solobolo", 10, 10);

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DimboRegular.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = 120;
        fontParameter.color = Color.BLACK;

        font = fontGenerator.generateFont(fontParameter);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        fontGenerator.dispose();

        // Socket event listners
        this.socket.on("game_data_in_room", onGameDataInRoom);
        this.socket.on("updated_data", onNewData);

        // init arraylist with game data
        this.socket.emit("get_game_data_in_room", EngineState.pin);
    }

    public void drawStats(Boolean multiplayer) {
        if(!multiplayer){
            font.draw(
                    EngineState.batch,
                    "Player: " + gameStat.getName() + "\nHitpoints: " + gameStat.getHitpoints()  + "\nCurrency: " + gameStat.getCurrency() ,
                    20f,
                    2530f
            );
        }else{
        float xPos = 20f;
        for (GameStat stat : gameStatList) {
            font.draw(
                    EngineState.batch,
                    "Player: " + stat.getName() + "\nHitpoints: " + stat.getHitpoints() + "\nCurrency: " + stat.getCurrency(),
                    xPos,
                    2530f
            );
            xPos += 1000f;
        }
    }}

    private ArrayList<GameStat> jsonArrayToArrayList(JSONArray array) throws JSONException {
        ArrayList<GameStat> gameStats = new ArrayList<>();

        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                gameStats.add(new GameStat(
                        object.getString("playerName"),
                        object.getInt("hitpoints"),
                        object.getInt("currency")
                ));
            }
        }

        return gameStats;
    }

    private Emitter.Listener onGameDataInRoom = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            try {
                gameStatList = jsonArrayToArrayList((JSONArray) args[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private Emitter.Listener onNewData = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            try {
                gameStatList = jsonArrayToArrayList((JSONArray) args[0]);
                drawStats(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
