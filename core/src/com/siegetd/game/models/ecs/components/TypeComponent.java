package com.siegetd.game.models.ecs.components;

import com.badlogic.ashley.core.Component;
import com.siegetd.game.models.ecs.entities.attacker.Attacker;
import com.siegetd.game.models.ecs.entities.defender.Defender;

public class TypeComponent implements Component {
    public Type type;

    public Defender defenderType;
    public Attacker attackerType;

    public TypeComponent(Type type){
        this.type = type;
    }

    public TypeComponent(Type type, Attacker attackerType){
        this.type = type;
        this.attackerType = attackerType;
        this.defenderType = null;
    }

    public TypeComponent(Type type, Defender defenderType){
        this.type = type;
        this.defenderType = defenderType;
    }
}
