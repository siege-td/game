package com.siegetd.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.models.map.tile.TileBorder;
import com.siegetd.game.views.components.ingame.AddEntityButton;

public class InputHandler {

    private PooledEngine engine;
    private OrthographicCamera camera;

    private Vector3 lastTouchCoordinates = null;

    private AddEntityButton addEntityButton;
    private Stage stage;

    public InputHandler(PooledEngine engine, OrthographicCamera camera, Stage stage) {
        this.engine = engine;
        this.camera = camera;
        this.stage = stage;
    }

    public void listen() {
        if (Gdx.input.justTouched()) {
            lastTouchCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        }

        if (lastTouchCoordinates != null) {
            // TODO: DO NOT CREATE IN UNDER ADD ENTITY BUTTON
            TileBorder tileBorder = new TileBorder(lastTouchCoordinates.x, lastTouchCoordinates.y, this.camera);
            tileBorder.drawTileBorder();

            // Draw add entity button
            // TODO: MOVE TO IN GAME UI CLASS


            addEntityButton = new AddEntityButton(camera);
            addEntityButton.addButtonListeners();

            stage.addActor(addEntityButton.button);

        }
    }
}
