package com.siegetd.game.models.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameMapActor extends Actor {

    private TiledMap tiledMap;
    private TiledMapTileLayer tiledMapTileLayer;
    private TiledMapTileLayer.Cell cell;

    public GameMapActor(TiledMap tiledMap, TiledMapTileLayer tiledMapTileLayer, TiledMapTileLayer.Cell cell) {
        this.tiledMap = tiledMap;
        this.tiledMapTileLayer = tiledMapTileLayer;
        this.cell = cell;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public void setTiledMap(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
    }

    public TiledMapTileLayer getTiledMapTileLayer() {
        return tiledMapTileLayer;
    }

    public void setTiledMapTileLayer(TiledMapTileLayer tiledMapTileLayer) {
        this.tiledMapTileLayer = tiledMapTileLayer;
    }

    public TiledMapTileLayer.Cell getCell() {
        return cell;
    }

    public void setCell(TiledMapTileLayer.Cell cell) {
        this.cell = cell;
    }
}
