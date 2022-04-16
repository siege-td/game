package com.siegetd.game.views.components;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.views.components.selectentity.SelectEntityModal;

public class AddEntityButton extends ButtonComponent {

    private ButtonComponent buttonComponent;
    private Texture buttonImg;
    public Button button;

    // Invisible rectangle used for click detection
    private ShapeRenderer shapeRenderer;
    private Rectangle transparentRectangle;

    private OrthographicCamera camera;
    private Stage stage;

    private SelectEntityModal selectEntityModal;

    public AddEntityButton(OrthographicCamera camera, Stage stage) {
        this.buttonComponent = new ButtonComponent();
        this.buttonImg = new Texture("GUI/button_play.png");
        this.button = this.buttonComponent.createButton(this.buttonImg);
        this.button.setSize(camera.viewportWidth / 80, camera.viewportWidth / 80);
        this.button.setPosition(10, 10);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setAutoShapeType(true);
        this.shapeRenderer.setProjectionMatrix(camera.combined);

        this.transparentRectangle = new Rectangle(
                0,
                0,
                (camera.viewportWidth / TILE_COLUMN) * 3,
                (camera.viewportHeight / TILE_ROW) * 2
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

        this.camera = camera;
        this.stage = stage;

        selectEntityModal = new SelectEntityModal(camera);
        selectEntityModal.addButtonListeners();
    }

    public void addButtonListeners() {
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(selectEntityModal.button);
            }
        });
    }

    public Rectangle getTransparentRectangle() {
        return transparentRectangle;
    }
}
