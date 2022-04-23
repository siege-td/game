package com.siegetd.game.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.siegetd.game.SiegeTd;
import com.siegetd.game.controllers.GameViewController;

public abstract class GameView {
    protected GameViewController gsc;
    protected SiegeTd app;
    protected SpriteBatch batch;

    protected GameView(GameViewController gsc){
        this.gsc = gsc;
        this.app = gsc.application();
        batch = app.getBatch();
    }
    public abstract void update(float delta);
    public abstract void render();
    public abstract void dispose();
}
