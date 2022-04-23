package com.siegetd.game.views.components.buttons.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.views.components.buttons.GameButton;

public class InputButton extends GameButton {

    private InputListener listener;

    public InputButton() {
        super("GUI/add_pin.png");
        this.listener = new InputListener();
    }

    public void addButtonListners() {
        this.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.getTextInput(listener,
                        "Enter PIN",
                        listener.getText(),
                        "");
            }
        });
    }

    public InputListener getListener() {
        return listener;
    }
}

