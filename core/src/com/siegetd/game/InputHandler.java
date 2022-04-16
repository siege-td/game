package com.siegetd.game;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.models.map.tile.TileBorder;
import com.siegetd.game.views.components.AddEntityButton;

public class InputHandler {

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Vector3 lastTouchCoordinates = null;

    private AddEntityButton addEntityButton;
    private Stage stage;

    public InputHandler(OrthographicCamera camera, Stage stage, SpriteBatch batch) {
        this.camera = camera;
        this.stage = stage;
        this.batch = batch;
    }

    public void listen() {
        if (Gdx.input.justTouched()) {
            lastTouchCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        }

        if (lastTouchCoordinates != null) {
            addEntityButton = new AddEntityButton(camera);
            addEntityButton.addButtonListeners(stage, batch);

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
                TileBorder tileBorder = new TileBorder(lastTouchCoordinates.x, lastTouchCoordinates.y, this.camera);
                tileBorder.drawTileBorder();

                stage.addActor(addEntityButton.button);
            }

        }
    }
}
