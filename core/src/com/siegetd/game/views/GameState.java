package com.siegetd.game.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.siegetd.game.SiegeTd;
import com.siegetd.game.controllers.GameStateController;

public abstract class GameState {
    protected GameStateController gsc;
    protected SiegeTd app;
    protected SpriteBatch batch;

    protected  GameState(GameStateController gsc){
        this.gsc = gsc;
        this.app = gsc.application();
        batch = app.getBatch();
    }

    public abstract void update(float delta);
    public abstract void render();
    public abstract void dispose();
}
