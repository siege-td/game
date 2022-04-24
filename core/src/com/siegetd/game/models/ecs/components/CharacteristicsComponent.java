package com.siegetd.game.models.ecs.components;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Component;

public class CharacteristicsComponent implements Component {
    public int towerRadius;
    public float attackRate;
    public int attackDamage;
    public float shotTimer;

    public CharacteristicsComponent(int towerRadius, float attackRate, int attackDamage){
        this.towerRadius = towerRadius * TILE_SIZE;
        this.attackRate = attackRate;
        this.attackDamage = attackDamage;
    }
}
