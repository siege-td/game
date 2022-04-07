package com.siegetd.game.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class RopeComponent {

    public int ropeWidth, ropeY, ropeLeftX, ropeRightX;
    public Texture img;

    public RopeComponent(WindowComponent table){
        img = new Texture("GUI/rope_big.png");
        ropeWidth =  img.getWidth();//(int) (img.getWidth()*0.75);
        ropeY =  (Gdx.graphics.getHeight()/ 2) + (table.windowHeight /3);
        ropeLeftX =  (Gdx.graphics.getWidth() / 2) - (table.windowWidth / 3);
        ropeRightX =  (Gdx.graphics.getWidth() / 2) + (table.windowWidth / 3)- img.getWidth();
    }

    public void dispose(){
        img.dispose();
    }
}
