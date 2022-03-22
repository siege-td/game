package com.siegetd.game.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ButtonComponent {

    protected ButtonComponent(){ }

    public Button createButton( Texture img) {
        Button button = new Button(
                new TextureRegionDrawable(
                        new TextureRegion(img)
                )
        );
        return button;
    }

    public Button createButton(Vector2 pos, Texture img) {
        Button button = new Button(
                new TextureRegionDrawable(
                        new TextureRegion(img)
                )
        );
        button.setSize(button.getWidth() / 2, button.getHeight() / 2);
        button.setPosition(
                (float) (pos.x - (button.getWidth()* 0.25)),
                (float) (pos.y - (button.getHeight() * 0.75))
        );
        return button;
    }

    public void dispose(Texture buttonImg) {
        buttonImg.dispose();
    }
}
