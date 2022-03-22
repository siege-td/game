package com.siegetd.game.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class RopeComponent {

    public int ropeWidth, ropeY, ropeLeftX, ropeRightX;
    public Texture img;

    public RopeComponent(TableComponent table){
        img = new Texture("GUI/rope_big.png");
        ropeWidth =  img.getWidth();//(int) (img.getWidth()*0.75);
        ropeY =  (Gdx.graphics.getHeight()/ 2) + (table.tableHeight/3);
        ropeLeftX =  (Gdx.graphics.getWidth() / 2) - (table.tableWidth / 3);
        ropeRightX =  (Gdx.graphics.getWidth() / 2) + (table.tableWidth / 3)- img.getWidth();
    }

    public void dispose(){
        img.dispose();
    }
}
