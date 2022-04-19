package com.siegetd.game.models.ECS.components;

import com.badlogic.ashley.core.Component;

public class TypeComponent implements Component {
    public Type type;

    public TypeComponent(Type type){
        this.type = type;
    }
}
