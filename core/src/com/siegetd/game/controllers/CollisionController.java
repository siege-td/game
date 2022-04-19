package com.siegetd.game.controllers;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.models.ECS.components.TransformComponent;
import com.siegetd.game.models.ECS.components.Type;
import com.siegetd.game.models.ECS.components.TypeComponent;
import com.siegetd.game.views.components.AddEntityButton;
import com.siegetd.game.views.components.map.TileBorder;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class CollisionController {

    private PooledEngine engine;

    public CollisionController(PooledEngine engine) {
        this.engine = engine;
    }

    //Responsible for centering rectangle surrounding entity
    public Vector2 transformPosition(Vector2 position){
        int X = (int) position.x - (TILE_SIZE / 2);
        int Y = (int) position.y - (TILE_SIZE / 2);
        return new Vector2(X, Y);
    }

    public void listen() {
        try{
            for (int i=0; i < engine.getEntities().size(); i++) {
                for (int j=0; j < engine.getEntities().size(); j++) {
                    if (engine.getEntities().get(i).getComponent(TypeComponent.class).type == Type.DEFENDER && engine.getEntities().get(j).getComponent(TypeComponent.class).type == Type.ATTACKER) {
                        Vector2 defenderPos = transformPosition(engine.getEntities().get(i).getComponent(TransformComponent.class).position);
                        Vector2 attackerPos = transformPosition(engine.getEntities().get(j).getComponent(TransformComponent.class).position);

                        java.awt.Rectangle towerRect = new Rectangle((int) defenderPos.x, (int) defenderPos.y, TILE_SIZE , TILE_SIZE);
                        java.awt.Rectangle attackerRect = new Rectangle((int) attackerPos.x, (int) attackerPos.y, TILE_SIZE , TILE_SIZE);

                        if (towerRect.intersects(attackerRect)) {
                            System.out.println("SHOOT!");
                            engine.removeEntity(engine.getEntities().get(j));
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
