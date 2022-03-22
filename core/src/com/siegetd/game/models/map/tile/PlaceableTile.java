package com.siegetd.game.models.map.tile;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class PlaceableTile extends Tile {
    public PlaceableTile(int x, int y, TiledMapTileLayer.Cell cell){
        super(x, y, cell);
    }
}