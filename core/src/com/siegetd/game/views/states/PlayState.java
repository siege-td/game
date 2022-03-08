package com.siegetd.game.views.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.views.GameState;

public class PlayState extends GameState{

    private CharSequence str;
    private final BitmapFont font;

    public PlayState(GameStateController gsc){
        super(gsc);
        font = new BitmapFont();
    }


    @Override
    public void handleInput() {

    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        str = "PLAY SCREEN";
        batch.begin();
        font.setColor(1f, 1f, 1f, 1f);
        font.draw(batch, str, (Gdx.graphics.getWidth() / 2f) - 50,  Gdx.graphics.getHeight() / 2f);
        batch.end();
    }

    @Override
    public void dispose() {
    }
}
