package com.siegetd.game.views.components.buttons.input;

import com.badlogic.gdx.Input;
import com.siegetd.game.SiegeTdState;

public class InputListener implements Input.TextInputListener {

    private String text = "No pin added";
    @Override
    public void input(String text) {
        this.text = text;
        SiegeTdState.pin = text;
    }

    @Override
    public void canceled() {
        text="No pin added";
    }

    public void incorrectPin() {
        text="Incorrect pin added";
    }

    public String getText(){
        return this.text;
    }

    public void pinAlreadyExists(){
        text = "Pin already exists";
    }

}
