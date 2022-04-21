package com.siegetd.game.views.components.ingame;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.EngineState;
import com.siegetd.game.controllers.LevelController;
import com.siegetd.game.views.components.ButtonComponent;

public class StartRoundButton extends ButtonComponent {

    private ButtonComponent buttonComponent;
    public Button button;

    private LevelController levelController;

    public StartRoundButton() {
        this.buttonComponent = new ButtonComponent();
        this.button = this.buttonComponent.createButton(new Texture("GUI/button_play.png"));
        this.button.setSize(EngineState.camera.viewportWidth / 80, EngineState.camera.viewportWidth / 80);
        this.button.setPosition(Gdx.graphics.getWidth() - (this.button.getWidth() + 10), 10);

        this.levelController = new LevelController(1);
    }

    public void addButtonListener() {
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelController.startRound();
            }
        });
    }
}
