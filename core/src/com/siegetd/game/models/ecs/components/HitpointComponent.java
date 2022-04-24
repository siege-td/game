package com.siegetd.game.models.ecs.components;

import com.badlogic.ashley.core.Component;

public class HitpointComponent implements Component {

    public int hitpoints = 0;

    public HitpointComponent(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getHitpoints(){
        return this.hitpoints;
    }

    public void setHitpoints(int hitpoints){
        this.hitpoints = hitpoints;
    }

    public void decreaseHitpoints(int damageTaken){
        this.hitpoints -= damageTaken;
    }
}
