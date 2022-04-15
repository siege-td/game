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
            addEntityButton = new AddEntityButton(camera);
            addEntityButton.addButtonListeners();

            // IF touching where add button should be, do not render tile border
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
                TileBorder tileBorder = new TileBorder(lastTouchCoordinates.x, lastTouchCoordinates.y, this.camera);
                tileBorder.drawTileBorder();

                stage.addActor(addEntityButton.button);
            }

        }
    }
}
