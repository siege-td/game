package com.siegetd.game.views.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

//TODO: Base the Y-coordinates and size(?) to table instead of graphics
public abstract class ButtonComponent {
    protected int btnWidth, btnHeight, btnX, btnY;
    protected Texture img;
    public Button btn;


    protected ButtonComponent(){

    }
    public abstract void dispose();

}
