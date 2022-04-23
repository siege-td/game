package com.siegetd.game.controllers.collision;

import com.badlogic.gdx.utils.Array;
import com.siegetd.game.EngineState;
import com.siegetd.game.models.ecs.components.HitpointComponent;
import com.siegetd.game.models.ecs.components.Type;
import com.siegetd.game.models.ecs.components.TypeComponent;

public class CollisionController {

    private Array<DefenderAi> defendAttackPairs = new Array<>();

    public CollisionController() {}

    public boolean checkIfExists(DefenderAi newDefendAttackPair){
        for (DefenderAi defendAttackPair : defendAttackPairs){
            if(defendAttackPair.getTarget().equals(newDefendAttackPair.getTarget()) && defendAttackPair.getDefender().equals(newDefendAttackPair.getDefender())){
                return true;
            }
        }

        return false;
    }

    private void updatePairArray(){
        Array<DefenderAi> defendAttackPairsCopy = new Array<>();
        for (DefenderAi defendAttackPair : defendAttackPairs){
            defendAttackPair.draw();

            if(defendAttackPair.getTarget().getComponent(HitpointComponent.class).hitpoints <= 0){
                defendAttackPairsCopy.add(defendAttackPair);
            }
        }

        for (DefenderAi defendAttackPair : defendAttackPairs){
            for (DefenderAi defendAttackPair1 : defendAttackPairsCopy){
                if(defendAttackPair.equals(defendAttackPair1)){
                    defendAttackPairs.removeValue(defendAttackPair, false);
                }
            }
        }
    }

    public void listen() {
        try{
            for (int i=0; i < EngineState.ecsEngine.getEntities().size(); i++) {
                    if (EngineState.ecsEngine.getEntities().get(i).getComponent(TypeComponent.class).type == Type.DEFENDER) {

                        DefenderAi defenderAi = new DefenderAi(EngineState.ecsEngine.getEntities().get(i));
                }
            }

            for (DefenderAi defenderAi : defendAttackPairs){
                defenderAi.findBestTarget();
                defenderAi.draw();
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
