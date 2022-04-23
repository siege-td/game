package com.siegetd.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.siegetd.game.controllers.GameViewController;

public class LobbyView extends GameView {

    private CharSequence str;
    private final BitmapFont font;

    public LobbyView(GameViewController gsc){
        super(gsc);
        font = new BitmapFont();
    }

    @Override
    public void update(float delta) {}

    @Override
    public void render() {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        str = "LOBBY SCREEN";
        batch.begin();
        font.setColor(1f, 1f, 1f, 1f);
        font.draw(batch, str, (Gdx.graphics.getWidth() / 2f) - 50,  Gdx.graphics.getHeight() / 2f);
        batch.end();
    }

    @Override
    public void dispose() {
    }
}
