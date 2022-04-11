package com.siegetd.game.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.siegetd.game.api.SocketConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class GameStats {

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private BitmapFont font;
    private SpriteBatch batch;

    private Socket socket;

    private String name = "";
    private int hitpoints = 0;
    private int currency = 0;

    public GameStats(SpriteBatch batch) throws URISyntaxException {
        this.socket = SocketConnection.getInstance().getSocket();

        this.batch = batch;

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DimboRegular.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = 16;
        fontParameter.color = Color.BLACK;

        font = fontGenerator.generateFont(fontParameter);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        fontGenerator.dispose();

        this.socket.on("updated_data", onNewData);
    }

    public void updateStats() {
        // Add logic for checking if the data is the same

        font.draw(
                batch,
                "Player: " + name + "\nHitpoints: " + hitpoints + "\nCurrency: " + currency,
                10f,
                470f
        );
    }

    private Emitter.Listener onNewData = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            try {
                JSONArray dataArray = (JSONArray) args[0];
                JSONObject data = dataArray.getJSONObject(0);
                String tempName = data.getString("playerName");
                int tempHitpoints = data.getInt("hitpoints");
                int tempCurrency = data.getInt("currency");

                if (!tempName.equalsIgnoreCase(name) || tempHitpoints != hitpoints || tempCurrency != currency) {
                    name = tempName;
                    hitpoints = tempHitpoints;
                    currency = tempCurrency;

                    updateStats();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };
}
