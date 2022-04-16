package com.siegetd.game.views.components.selectentity;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.views.components.ButtonComponent;

public class SelectEntityModal {

    private OrthographicCamera camera;

    private ButtonComponent buttonComponent;
    private Texture buttonImg;
    public Button button;

    public SelectEntityModal(OrthographicCamera camera) {
        this.camera = camera;

        this.buttonComponent = new ButtonComponent();
        this.buttonImg = new Texture("GUI/add_mage.png");
        this.button = this.buttonComponent.createButton(buttonImg);
        this.button.setSize(camera.viewportWidth / 80, camera.viewportWidth / 80);
        this.button.setPosition(
                ((Gdx.graphics.getWidth() / 2) - (this.button.getWidth() / 2)),
                ((Gdx.graphics.getHeight() / 2) - ((this.button.getHeight() / 2)))
        );

    }

    public void addButtonListeners(final PooledEngine engine) {
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Add mage clicked");
            }
        });
    }

    public void hideModal() {

    }
}
