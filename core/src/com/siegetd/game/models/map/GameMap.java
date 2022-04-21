package com.siegetd.game.models.map;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.models.map.tile.MovableTile;

import java.util.LinkedList;
import java.util.List;

public class GameMap extends Stage {
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private List<MovableTile> movableTiles;

    public GameMap(OrthographicCamera camera){
        tiledMap = new TmxMapLoader().load("level1/level1map.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        this.camera = camera;
        this.camera.setToOrtho(false, TILE_COLUMN * TILE_SIZE, TILE_ROW * TILE_SIZE);
        //this.camera.update();

        this.movableTiles = generateMovableTiles();
    }

    private List<MovableTile> generateMovableTiles(){
        MapLayers mapLayers = tiledMap.getLayers();
        TiledMapTileLayer moveableLayer = (TiledMapTileLayer) mapLayers.get("Moveable");
        TiledMapTileLayer endLayer = (TiledMapTileLayer) mapLayers.get("End");

        LinkedList<MovableTile> movables = new LinkedList<>();

        for (int col = 0; col < TILE_COLUMN; col++) {
            for (int row = 0; row < TILE_ROW; row++) {
                TiledMapTileLayer.Cell movableCell = moveableLayer.getCell(col, row);
                TiledMapTileLayer.Cell endCell = endLayer.getCell(col, row);

                if (movableCell != null) {
                    MovableTile movableTile = new MovableTile(col, row, movableCell);
                    movables.add(movableTile);
                }
                if (endCell != null) {
                    MovableTile endMovableTile = new MovableTile(col, row, endCell);
                    movables.add(endMovableTile);
                }
            }
        }

        return movables;
    }

    public List<MovableTile> getMovableTiles(){
        return this.movableTiles;
    }

    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }
}