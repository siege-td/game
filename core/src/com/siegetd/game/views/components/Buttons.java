package com.siegetd.game.views.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public abstract class Buttons {
    protected int btnWidth, btnHeight, btnX, btnY;
    protected Texture img;
    public Button btn;

    protected Buttons(){

    }
    public abstract void dispose();

}
