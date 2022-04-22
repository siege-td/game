package com.siegetd.game.controllers;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.siegetd.game.EngineState;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.models.ecs.components.TypeComponent;
import com.siegetd.game.models.map.GameMap;
import com.siegetd.game.views.components.AddEntityButton;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class InputController {

    private AddEntityButton addEntityButton;

    private GameMap gameMap;

    public InputController(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void listen() {
        if (Gdx.input.justTouched()) {

            //Uncheck hover effect on second click
            if(this.gameMap.getSelectedCell() != null){
                this.gameMap.unselectCell();
                if(addEntityButton.button != null) {
                    addEntityButton.button.setVisible(false);
                }
                return;
            }
            addEntityButton = new AddEntityButton();
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

            EngineState.camera.unproject(mousePos);

            this.gameMap.selectedCellX = (int) mousePos.x/TILE_SIZE;
            this.gameMap.selectedCellY = (int) mousePos.y/TILE_SIZE;

            // Check if turret is in pressed tile
            if(turretInTile()){
                return;
            }

            // Check if tile is placeable
            TiledMapTileLayer tileLayer = (TiledMapTileLayer) this.gameMap.getTileLayers("Placeable");
            this.gameMap.setSelectedCell(tileLayer.getCell(this.gameMap.selectedCellX, this.gameMap.selectedCellY));
            if(this.gameMap.getSelectedCell() == null){
                return;
            }
            this.gameMap.selectCell();
            EngineState.stage.addActor(addEntityButton.button);
            int tileX = this.gameMap.selectedCellX * TILE_SIZE;
            int tileY = this.gameMap.selectedCellY * TILE_SIZE;
            addEntityButton.addButtonListeners(new Vector2(tileX, tileY), onEntitySpawned());
        }
    }

    public Callable<Void> onEntitySpawned() {
        return null;
    }

    /**
     * Loop through defenders to see if they are placed on same tile as pressed
     * @return return true if turret is on tile
     */
    private boolean turretInTile(){
        ImmutableArray<Entity> entities = EngineState.ecsEngine.getEntities();
        ArrayList<Entity> defenders = new ArrayList<>();

        for (Entity entity : entities) {
            for (Component component : entity.getComponents()) {
                if (component.getClass() != TypeComponent.class) {
                    defenders.add(entity);
                }
            }
        }

        for (Entity defender : defenders) {
            int defenderX = (int) defender.getComponent(TransformComponent.class).position.x;
            int defenderY = (int) defender.getComponent(TransformComponent.class).position.y;
            if(defenderX == this.gameMap.selectedCellX*TILE_SIZE && defenderY == this.gameMap.selectedCellY*TILE_SIZE) {
                return true;
            }
        }
        return false;
    }
}
