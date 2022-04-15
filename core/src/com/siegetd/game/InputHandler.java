package com.siegetd.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class InputHandler {

    private PooledEngine engine;
    private OrthographicCamera camera;

    public InputHandler(PooledEngine engine, OrthographicCamera camera) {
        this.engine = engine;
        this.camera = camera;
    }

    public void listen() {
        if (Gdx.input.justTouched()) {
            Vector3 touchCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            // 1. check if tile clicked is placable
            // 2. add square around tile clicked
            // 3. open menu for selecting entity to spawn at placable tile
            // 4. spawn selected entity in center of clicked tile
        }
    }
}
