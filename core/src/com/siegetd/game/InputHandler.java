package com.siegetd.game;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.models.map.tile.TileBorder;
import com.siegetd.game.views.components.AddEntityButton;

import java.util.ArrayList;

public class InputHandler {

    private OrthographicCamera camera;

    private Vector3 lastTouchCoordinates = null;

    private AddEntityButton addEntityButton;
    private Stage stage;

    private ArrayList<Vector3> touchCoordinates;

    private float tileX = 0f;
    private float tileY = 0f;
    private boolean tilePosSet = false;

    public InputHandler(OrthographicCamera camera, Stage stage, PooledEngine engine) {
        this.camera = camera;
        this.stage = stage;

        this.touchCoordinates = new ArrayList<>();

        addEntityButton = new AddEntityButton(camera, stage);
        addEntityButton.addButtonListeners(engine);
    }

    public void listen() {
        if (Gdx.input.justTouched()) {
            lastTouchCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            touchCoordinates.add(lastTouchCoordinates);
        }

        if (lastTouchCoordinates != null) {
            // If touching where add button should be, do not render tile border
            // TODO: keep tile border in place when touching under button
            if (!Intersector.intersectRectangles(
                    addEntityButton.getTransparentRectangle(),
                    new Rectangle(
                            lastTouchCoordinates.x - (lastTouchCoordinates.x % (camera.viewportWidth / TILE_COLUMN)),
                            lastTouchCoordinates.y - (lastTouchCoordinates.y % (camera.viewportHeight / TILE_ROW)),
                            camera.viewportWidth / TILE_COLUMN,
                            camera.viewportHeight / TILE_ROW
                    ),
                    addEntityButton.getTransparentRectangle()
            )) {
                stage.addActor(addEntityButton.button);

                if (!tilePosSet) {
                    tileX = lastTouchCoordinates.x;
                    tileY = lastTouchCoordinates.y;
                }
            } else {
                tileX = touchCoordinates.get(touchCoordinates.size() - 2).x;
                tileY = touchCoordinates.get(touchCoordinates.size() - 2).y;
                tilePosSet = true;
            }
            TileBorder tileBorder = new TileBorder(
                    tileX,
                    tileY,
                    this.camera
            );
            tileBorder.drawTileBorder();
        }
    }
}
