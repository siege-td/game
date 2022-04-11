package com.siegetd.game.views.components;

import com.badlogic.gdx.Input;

public class InputListener implements Input.TextInputListener {

    private String text = "No pin added";
    @Override
    public void input(String text) {
        this.text = text;
    }

    @Override
    public void canceled() {
        text="No pin added";
    }

    public String getText(){
        return this.text;
    }
}
