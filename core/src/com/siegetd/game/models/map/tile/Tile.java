package com.siegetd.game.models.map.tile;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public abstract class Tile {
    private int x, y;
    private TiledMapTileLayer.Cell cell;

    public Tile(int x, int y, TiledMapTileLayer.Cell cell){
        this.x = x * TILE_SIZE;
        this.y = y * TILE_SIZE;
        this.cell = cell;
    }

    public  TiledMapTileLayer.Cell getCell(){
        return this.cell;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    @Override
    public String toString(){
        return "POSITION: (" + x / TILE_SIZE + ", " + y / TILE_SIZE + ")";
    }
}