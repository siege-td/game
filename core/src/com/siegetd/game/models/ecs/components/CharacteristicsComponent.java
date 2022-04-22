package com.siegetd.game.models.ecs.components;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Component;
import com.siegetd.game.models.ecs.entities.attacker.Attacker;
import com.siegetd.game.models.ecs.entities.defender.Defender;

public class CharacteristicsComponent implements Component {
    public int tower_radius;
    public int attack_speed;
    public int attack_damage;

    public CharacteristicsComponent(int tower_radius, int attack_speed, int attack_damage){
        this.tower_radius = tower_radius * TILE_SIZE;
        this.attack_speed = attack_speed;
        this.attack_damage = attack_damage;
    }
}
