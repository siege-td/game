package com.siegetd.game.views.components.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public abstract class GameButton {

    private Button button;

    public GameButton(String buttonImagePath) {
        button = new Button(new TextureRegionDrawable(new Texture(buttonImagePath)));
    }

    public Button getButton() {
        return button;
    }
}
