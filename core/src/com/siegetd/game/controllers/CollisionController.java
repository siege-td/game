package com.siegetd.game.controllers;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
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
    private Array<DefendAttackPair> defendAttackPairs = new Array<>();
    private SpriteBatch batch;
    private Texture img = new Texture(Gdx.files.internal("ammo/stone_small.png"));
    private OrthographicCamera camera;

    public CollisionController(PooledEngine engine, SpriteBatch batch, OrthographicCamera camera) {
        this.engine = engine;
        this.batch = batch;
        this.camera = camera;
    }

    //Responsible for centering rectangle surrounding entity
    public Vector2 transformPosition(Vector2 position){
        int X = (int) position.x - (TILE_SIZE / 2);
        int Y = (int) position.y - (TILE_SIZE / 2);
        return new Vector2(X, Y);
    }

    public void update(){

        /*
         try{
            for (DefendAttackPair defendAttackPair : defendAttackPairs){
                batch.begin();
                batch.draw(img, camera.viewportWidth / 2, camera.viewportHeight / 2, img.getWidth(), img.getHeight());
                batch.end();
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
         */

        batch.begin();
        batch.draw(img, camera.viewportWidth / 2, camera.viewportHeight / 2, img.getWidth(), img.getHeight());
        batch.end();

    }

    public boolean checkIfExists(DefendAttackPair newDefendAttackPair){
        for (DefendAttackPair defendAttackPair : defendAttackPairs){
            if(defendAttackPair.attacker.equals(newDefendAttackPair.attacker) && defendAttackPair.defender.equals(newDefendAttackPair.defender)){
                return true;
            }
        }

        return false;
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
                            DefendAttackPair newDefendAttackPair = new DefendAttackPair(engine.getEntities().get(i), engine.getEntities().get(j));

                            if(defendAttackPairs.size == 0){
                                defendAttackPairs.add(newDefendAttackPair);
                            } else {
                                if(!checkIfExists(newDefendAttackPair)){
                                    defendAttackPairs.add(newDefendAttackPair);
                                }
                            }

                            System.out.println(defendAttackPairs.size);

                            //System.out.println(engine.getEntities().size());
                            System.out.println(defendAttackPairs.size);
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
