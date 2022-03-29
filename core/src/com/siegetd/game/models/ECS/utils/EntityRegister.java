package com.siegetd.game.models.ecs.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

public class EntityRegister {

    private final PooledEngine engine;

    public EntityRegister(PooledEngine engine) {
        this.engine = engine;
    }

    public Entity getByIndex(int index) {
        return engine.getEntities().get(index);
    }
}
