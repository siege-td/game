package com.siegetd.game.models.map.tile;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class MovableTile extends Tile {
    public MovableTile(int x, int y, TiledMapTileLayer.Cell cell){
        super(x, y, cell);
    }
}