package com.siegetd.game.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TableComponent {

    public Texture img;
    public int tableWidth, tableHeight, tableX, tableY;

    public TableComponent(){
        img = new Texture("GUI/table.png");
        tableHeight = (int)(Gdx.graphics.getHeight() * 0.75);
        tableWidth = (int)(Gdx.graphics.getWidth() * 0.65);
        tableX = (Gdx.graphics.getWidth() / 2) - (tableWidth / 2);
        tableY = (Gdx.graphics.getHeight()/ 2) - (tableHeight / 2);
    }

    public void dispose(){
        img.dispose();
    }

}
