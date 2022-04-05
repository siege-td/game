package com.siegetd.game.views.components;

import com.badlogic.gdx.Input;

public class InputListener implements Input.TextInputListener {

    private String text = "default";
    @Override
    public void input(String text) {
        this.text = text;
    }

    @Override
    public void canceled() {
        text="Canceled";
    }

    public String getText(){
        return this.text;
    }
}
