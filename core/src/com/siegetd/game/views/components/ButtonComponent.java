package com.siegetd.game.views.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ButtonComponent {

    public ButtonComponent(){ }

    public Button createButton( Texture img) {
        Button button = new Button(
                new TextureRegionDrawable(
                        new TextureRegion(img)
                )
        );
        return button;
    }

    public void dispose(Texture buttonImg) {
        buttonImg.dispose();
    }
}
