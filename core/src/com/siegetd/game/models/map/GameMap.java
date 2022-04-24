package com.siegetd.game.models.map;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.models.map.tile.MovableTile;

import java.util.LinkedList;
import java.util.List;

public class GameMap extends Stage {
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private List<MovableTile> movableTiles;

    public int selectedCellX;
    public int selectedCellY;
    private TiledMapTileLayer.Cell selectedCell;

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

    /**
     * Adds highlighting to a chosen cell
     */
    public void selectCell(){
        TextureRegion selectedTileTexture = new TextureRegion(new Texture("level1/tiles/43.png"));
        StaticTiledMapTile selectedTile = new StaticTiledMapTile(selectedTileTexture);
        selectedCell.setTile(selectedTile);
    }

    /**
     * Removes highlight from a chosen cell
     */
    public void unselectCell(){
        TextureRegion unselectedTileTexture = new TextureRegion(new Texture("level1/tiles/22.png"));
        StaticTiledMapTile unselectedTile = new StaticTiledMapTile(unselectedTileTexture);
        selectedCell.setTile(unselectedTile);
        selectedCell = null;
    }

    /**
     * Get the currently selected cell
     * @return selected cell
     */
    public TiledMapTileLayer.Cell getSelectedCell(){
        return selectedCell;
    }

    /**
     * Sets the selected cell
     * @param newCell the new cell to set to
     */
    public void setSelectedCell(TiledMapTileLayer.Cell newCell){
        this.selectedCell = newCell;
    }

    /**
     * returns the maplayers for the selected layers
     * @param layer the layer to get
     * @return Maplayer for the selected layer
     */
    public MapLayer getTileLayers(String layer) {
        return tiledMap.getLayers().get(layer);
    }

    /**
     * Returns the movable tiles for the path
     * @return the movable tiles
     */
    public List<MovableTile> getMovableTiles(){
        return this.movableTiles;
    }

    public Vector2 getEndPosition(){
        MovableTile endCell = this.movableTiles.get(this.movableTiles.size()-1);
        Vector2 endPos = new Vector2(endCell.getX(), endCell.getY());
        return endPos;
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