package com.siegetd.game.controllers;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.siegetd.game.EngineState;
import com.siegetd.game.models.ecs.components.HitpointComponent;
import com.siegetd.game.models.ecs.components.Type;
import com.siegetd.game.models.ecs.components.TypeComponent;

public class CollisionController {

    private Array<DefendAttackPair> defendAttackPairs = new Array<>();

    public CollisionController() {}

    public boolean checkIfExists(DefendAttackPair newDefendAttackPair){
        for (DefendAttackPair defendAttackPair : defendAttackPairs){
            if(defendAttackPair.attacker.equals(newDefendAttackPair.attacker) && defendAttackPair.defender.equals(newDefendAttackPair.defender)){
                return true;
            }
        }

        return false;
    }

    private void updatePairArray(){
        Array<DefendAttackPair> defendAttackPairsCopy = new Array<>();
        for (DefendAttackPair defendAttackPair : defendAttackPairs){
            defendAttackPair.draw();

            if(defendAttackPair.attacker.getComponent(HitpointComponent.class).hitpoints <= 0){
                defendAttackPairsCopy.add(defendAttackPair);
            }
        }

        for (DefendAttackPair defendAttackPair : defendAttackPairs){
            for (DefendAttackPair defendAttackPair1 : defendAttackPairsCopy){
                if(defendAttackPair.equals(defendAttackPair1)){
                    defendAttackPairs.removeValue(defendAttackPair, false);
                }
            }
        }
    }

    public void listen() {
        try{
            for (int i=0; i < EngineState.ecsEngine.getEntities().size(); i++) {
                for (int j=0; j < EngineState.ecsEngine.getEntities().size(); j++) {
                    if (EngineState.ecsEngine.getEntities().get(i).getComponent(TypeComponent.class).type == Type.DEFENDER && EngineState.ecsEngine.getEntities().get(j).getComponent(TypeComponent.class).type == Type.ATTACKER) {

                        DefendAttackPair newDefendAttackPair = new DefendAttackPair(EngineState.ecsEngine.getEntities().get(i), EngineState.ecsEngine.getEntities().get(j));

                        if(!checkIfExists(newDefendAttackPair)){
                            defendAttackPairs.add(newDefendAttackPair);
                        }
                    }
                }
            }
            updatePairArray();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
