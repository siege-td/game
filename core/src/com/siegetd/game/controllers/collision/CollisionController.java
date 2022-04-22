package com.siegetd.game.controllers.collision;

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
            if(defendAttackPair.getAttacker().equals(newDefendAttackPair.getAttacker()) && defendAttackPair.getDefender().equals(newDefendAttackPair.getDefender())){
                return true;
            }
        }

        return false;
    }

    private void updatePairArray(){
        Array<DefendAttackPair> defendAttackPairsCopy = new Array<>();
        for (DefendAttackPair defendAttackPair : defendAttackPairs){
            defendAttackPair.draw();

            if(defendAttackPair.getAttacker().getComponent(HitpointComponent.class).hitpoints <= 0){
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
            Array<DefendAttackPair> defendAttackPairsCopy = new Array<>();
            for (DefendAttackPair defendAttackPair : defendAttackPairs){
                defendAttackPair.draw();

                if(defendAttackPair.getAttacker().getComponent(HitpointComponent.class).hitpoints <= 0){
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
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
