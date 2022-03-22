package com.siegetd.game.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

//TODO: Base the Y-coordinates and size(?) to table instead of graphics
public class ButtonComponent {

    protected ButtonComponent(){ }

    public Button createButton(float yScale, Texture img) {
        Button button = new Button(
                new TextureRegionDrawable(
                        new TextureRegion(img)
                )
        );

        button.setSize(button.getWidth() / 2, button.getHeight() / 2);
        button.setPosition(
                (Gdx.graphics.getWidth() / 2) - (button.getWidth() / 2),
                (Gdx.graphics.getHeight() * yScale) - (button.getHeight() / 2)
                );

        return button;
    }

    public void dispose(Texture buttonImg) {
        buttonImg.dispose();
    }
}
