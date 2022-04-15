package com.siegetd.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.siegetd.game.models.map.tile.TileBorder;

public class InputHandler {

    private PooledEngine engine;
    private OrthographicCamera camera;

    private Vector3 lastTouchCoordinates = null;

    public InputHandler(PooledEngine engine, OrthographicCamera camera) {
        this.engine = engine;
        this.camera = camera;
    }

    public void listen() {
        if (Gdx.input.justTouched()) {
            lastTouchCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            System.out.println(lastTouchCoordinates);
            // 1. check if tile clicked is placable
            // 2. add square around tile clicked
            // 3. open menu for selecting entity to spawn at placable tile
            // 4. spawn selected entity in center of clicked tile
        }

        if (lastTouchCoordinates != null) {
            TileBorder tileBorder = new TileBorder(lastTouchCoordinates.x, lastTouchCoordinates.y, this.camera);
            tileBorder.drawTileBorder();
        }
    }
}
