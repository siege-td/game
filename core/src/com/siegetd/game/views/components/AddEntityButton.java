package com.siegetd.game.views.components;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.EngineState;
import com.siegetd.game.views.components.selectentity.SelectEntityModal;

import java.util.concurrent.Callable;

public class AddEntityButton extends ButtonComponent {

    private ButtonComponent buttonComponent;
    private Texture buttonImg;
    public Button button;

    // Invisible rectangle used for click detection
    private ShapeRenderer shapeRenderer;
    private Rectangle transparentRectangle;

    private SelectEntityModal selectEntityModal = null;

    public AddEntityButton() {
        this.buttonComponent = new ButtonComponent();
        this.buttonImg = new Texture("GUI/open_shop.png");
        this.button = this.buttonComponent.createButton(this.buttonImg);
        this.button.setSize(EngineState.camera.viewportWidth / 80, EngineState.camera.viewportWidth / 80);
        this.button.setPosition(10, 10);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setAutoShapeType(true);
        this.shapeRenderer.setProjectionMatrix(EngineState.camera.combined);

        this.transparentRectangle = new Rectangle(
                0,
                0,
                (EngineState.camera.viewportWidth / TILE_COLUMN) * 3,
                (EngineState.camera.viewportHeight / TILE_ROW) * 2
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
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectEntityModal == null) {
                    selectEntityModal = new SelectEntityModal();
                    selectEntityModal.showModal();
                    selectEntityModal.addButtonListeners(entitySpawnPos);
                    EngineState.stage.addActor(selectEntityModal.getArcherButton());
                    EngineState.stage.addActor(selectEntityModal.getMageButton());
                    EngineState.stage.addActor(selectEntityModal.getZappButton());
                    addStageListeners();
                }
            }
        });
    }

    private void addStageListeners(){
        Gdx.input.setInputProcessor(EngineState.stage);
        EngineState.stage.addListener(new ClickListener(){
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
