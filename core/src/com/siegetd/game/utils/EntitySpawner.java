package com.siegetd.game.utils;

import com.badlogic.ashley.core.PooledEngine;
import com.siegetd.game.models.ecs.components.Type;

public class EntitySpawner {

    private PooledEngine engine;

    public EntitySpawner(PooledEngine engine) {
        this.engine = engine;
    }

    public void spawn(float x, float y, Type entityType) {

    }
}
