package com.siegetd.game.controllers;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.EngineState;
import com.siegetd.game.views.components.map.TileBorder;
import com.siegetd.game.views.components.AddEntityButton;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class InputController {

    private Vector3 lastTouchCoordinates = null;

    private AddEntityButton addEntityButton;

    private ArrayList<Vector3> touchCoordinates;

    private float tileX = 0f;
    private float tileY = 0f;
    private boolean tilePosSet = false;

    public InputController() {
        this.touchCoordinates = new ArrayList<>();
    }

    public void listen() {
        if (Gdx.input.justTouched()) {
            lastTouchCoordinates = EngineState.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            touchCoordinates.add(lastTouchCoordinates);
            addEntityButton = new AddEntityButton();
        }

        if (lastTouchCoordinates != null) {
            // If touching where add button should be, do not render tile border
            if (!Intersector.intersectRectangles(
                    addEntityButton.getTransparentRectangle(),
                    new Rectangle(
                            lastTouchCoordinates.x - (lastTouchCoordinates.x % (EngineState.camera.viewportWidth / TILE_COLUMN)),
                            lastTouchCoordinates.y - (lastTouchCoordinates.y % (EngineState.camera.viewportHeight / TILE_ROW)),
                            EngineState.camera.viewportWidth / TILE_COLUMN,
                            EngineState.camera.viewportHeight / TILE_ROW
                    ),
                    addEntityButton.getTransparentRectangle()
            )) {
                if (!tilePosSet) {
                    tileX = lastTouchCoordinates.x;
                    tileY = lastTouchCoordinates.y;
                }
            } else {
                tileX = touchCoordinates.get(touchCoordinates.size() - 2).x;
                tileY = touchCoordinates.get(touchCoordinates.size() - 2).y;
                tilePosSet = true;
            }

            EngineState.stage.addActor(addEntityButton.button);
            addEntityButton.addButtonListeners(new Vector2(tileX, tileY));

            TileBorder tileBorder = new TileBorder(
                    tileX,
                    tileY
            );

            tileBorder.drawTileBorder();
        }
    }

}
