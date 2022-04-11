package com.siegetd.game.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class WindowComponent {

    public Texture img;
    private Vector2 pos;
    public int windowWidth, windowHeight, windowX, windowY;

    public WindowComponent(){
        img = new Texture("GUI/table.png");
        windowHeight = (int)(Gdx.graphics.getHeight() * 0.75);
        windowWidth = (int)(Gdx.graphics.getWidth() * 0.65);
        windowX = (Gdx.graphics.getWidth() / 2) - (windowWidth / 2);
        windowY = (Gdx.graphics.getHeight()/ 2) - (windowHeight / 2);
    }

    public Vector2 getTopLeft(){
        pos = new Vector2();
        pos.x = windowX;
        pos.y = windowY + this.windowHeight;
        return pos;
    }
    public Vector2 getBottomCenter(){
        pos = new Vector2();
        pos.x = windowX + (this.windowWidth /2);
        pos.y = windowY;
        return pos;
    }

    public void dispose(){
        img.dispose();
    }

}
