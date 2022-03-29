package com.siegetd.game.views.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class TableComponent {

    public Texture img;
    private Vector2 pos;
    public int tableWidth, tableHeight, tableX, tableY;

    public TableComponent(){
        img = new Texture("GUI/table.png");
        tableHeight = (int)(Gdx.graphics.getHeight() * 0.75);
        tableWidth = (int)(Gdx.graphics.getWidth() * 0.65);
        tableX = (Gdx.graphics.getWidth() / 2) - (tableWidth / 2);
        tableY = (Gdx.graphics.getHeight()/ 2) - (tableHeight / 2);
    }

    public Vector2 getTopLeft(){
        pos = new Vector2();
        pos.x = tableX;
        pos.y = tableY + this.tableHeight;
        return pos;
    }
    public Vector2 getBottomCenter(){
        pos = new Vector2();
        pos.x = tableX + (this.tableWidth/2);
        pos.y = tableY;
        return pos;
    }

    public void dispose(){
        img.dispose();
    }

}
