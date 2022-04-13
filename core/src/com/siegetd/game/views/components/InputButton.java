package com.siegetd.game.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.controllers.GameStateController;

public class InputButton extends ButtonComponent {

    private ButtonComponent buttonComponent;
    private Texture buttonImg;
    public InputListener listener;
    public Button button;

    public InputButton() {

        this.buttonComponent = new ButtonComponent();
        this.buttonImg = new Texture("GUI/add_pin.png");
        this.button = this.buttonComponent.createButton( this.buttonImg);
        this.listener = new InputListener();
    }

    public void addButtonListners(final GameStateController gsc) {
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.getTextInput(listener,
                        "Enter PIN",
                        listener.getText(),
                        "");
            }
        });
    }
}

