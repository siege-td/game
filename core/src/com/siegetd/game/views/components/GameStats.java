package com.siegetd.game.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.siegetd.game.api.SocketConnection;

import java.net.URISyntaxException;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class GameStats {

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private BitmapFont font;
    private SpriteBatch batch;

    private Socket socket;

    public GameStats(SpriteBatch batch) throws URISyntaxException {
        this.socket = SocketConnection.getInstance().getSocket();

        this.batch = batch;

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DimboRegular.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = 12;
        fontParameter.color = Color.BLACK;

        font = fontGenerator.generateFont(fontParameter);

        fontGenerator.dispose();

        subscribeToSocketEvents();
    }

    public void initStats() {
        font.draw(
                batch,
                "Hellooooooo",
                20f,
                20f
        );
    }

    private void subscribeToSocketEvents() {
        socket.on("updated_data", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println(args);
            }
        });
    }
}
