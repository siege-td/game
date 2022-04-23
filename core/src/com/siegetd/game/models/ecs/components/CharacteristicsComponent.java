package com.siegetd.game.models.ecs.components;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Component;
import com.siegetd.game.models.ecs.entities.attacker.Attacker;
import com.siegetd.game.models.ecs.entities.defender.Defender;

public class CharacteristicsComponent implements Component {
    public int tower_radius;
    public float attack_rate;
    public int attack_damage;
    public float shotTimer;

    public CharacteristicsComponent(int tower_radius, float attack_rate, int attack_damage){
        this.tower_radius = tower_radius * TILE_SIZE;
        this.attack_rate = attack_rate;
        this.attack_damage = attack_damage;
    }
}
