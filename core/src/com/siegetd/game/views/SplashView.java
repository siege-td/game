package com.siegetd.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.siegetd.game.controllers.GameViewController;

public class SplashView extends GameView {
    float acc = 0f;
    CharSequence str;
    BitmapFont font;
    final int COUNTDOWN = 1;

    public SplashView(GameViewController gsc){
        super(gsc);
        str = "";
        font = new BitmapFont();
    }


    @Override
    public void update(float delta) {
        acc += delta;
        if(acc >= COUNTDOWN){
            gsc.setState(GameViewController.View.MENU);
        }

        str = "SPLASHSCREEN " + "\n" + (COUNTDOWN - acc) + "";
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.setColor(0f, 0f, 0f, 1f);
        font.draw(batch, str, (Gdx.graphics.getWidth() / 2f) - str.length() / 2f,  Gdx.graphics.getHeight() / 2f);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
