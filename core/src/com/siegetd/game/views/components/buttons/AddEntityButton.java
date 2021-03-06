package com.siegetd.game.views.components.buttons;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.SiegeTdState;
import com.siegetd.game.views.components.selectentity.SelectEntityModal;

public class AddEntityButton extends GameButton {

    // Invisible rectangle used for click detection
    private ShapeRenderer shapeRenderer;
    private Rectangle transparentRectangle;

    private SelectEntityModal selectEntityModal = null;

    public AddEntityButton() {
        super("GUI/open_shop.png");
        getButton().setSize(SiegeTdState.camera.viewportWidth / 80, SiegeTdState.camera.viewportWidth / 80);
        getButton().setPosition(10, 10);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setAutoShapeType(true);
        this.shapeRenderer.setProjectionMatrix(SiegeTdState.camera.combined);

        this.transparentRectangle = new Rectangle(
                0,
                0,
                (SiegeTdState.camera.viewportWidth / TILE_COLUMN) * 3,
                (SiegeTdState.camera.viewportHeight / TILE_ROW) * 2
        );

        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 1, 0, 0f));
        shapeRenderer.rect(
                transparentRectangle.getX(),
                transparentRectangle.getY(),
                transparentRectangle.getWidth(),
                transparentRectangle.getHeight()
        );
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public void addButtonListeners(final Vector2 entitySpawnPos) {
        getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectEntityModal == null) {
                    selectEntityModal = new SelectEntityModal();
                    selectEntityModal.showModal();
                    selectEntityModal.addButtonListeners(entitySpawnPos);
                    SiegeTdState.stage.addActor(selectEntityModal.getButtonTable());
                    addStageListeners();
                }
            }
        });
    }

    private void addStageListeners(){
        Gdx.input.setInputProcessor(SiegeTdState.stage);
        SiegeTdState.stage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Hide building options
                selectEntityModal.hideModal();
            }
        });
    }

    public Rectangle getTransparentRectangle() {
        return transparentRectangle;
    }
}
