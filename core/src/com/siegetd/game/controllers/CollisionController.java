package com.siegetd.game.controllers;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.siegetd.game.models.ecs.components.Type;
import com.siegetd.game.models.ecs.components.TypeComponent;

public class CollisionController {

    private PooledEngine engine;
    private Array<DefendAttackPair> defendAttackPairs = new Array<>();
    private SpriteBatch batch;
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

                        DefendAttackPair newDefendAttackPair = new DefendAttackPair(batch, engine.getEntities().get(i), engine.getEntities().get(j));

                        if(!checkIfExists(newDefendAttackPair)){
                            defendAttackPairs.add(newDefendAttackPair);
                        }

                        try{
                            for (DefendAttackPair defendAttackPair : defendAttackPairs){
                                defendAttackPair.draw();
                            }
                        } catch (Exception e){
                            System.out.println(e.toString());
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
