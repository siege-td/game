package com.siegetd.game.views.components.map;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.siegetd.game.EngineState;

public class TileBorder {

    private ShapeRenderer shapeRenderer;
    private Rectangle rectangle;

    public TileBorder(float x, float y) {
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setAutoShapeType(true);
        this.shapeRenderer.setProjectionMatrix(EngineState.camera.combined);

        this.rectangle = new Rectangle(
                x - (x % (EngineState.camera.viewportWidth / TILE_COLUMN)),
                y - (y % (EngineState.camera.viewportHeight / TILE_ROW)),
                EngineState.camera.viewportWidth / TILE_COLUMN,
                EngineState.camera.viewportHeight / TILE_ROW
        );
    }

    public void drawTileBorder() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(
                rectangle.getX(),
                rectangle.getY(),
                rectangle.getWidth(),
                rectangle.getHeight()
        );
        shapeRenderer.end();
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
