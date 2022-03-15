package com.siegetd.game.models.ecs.utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.siegetd.game.models.ecs.components.TransformComponent;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {
    private ComponentMapper<TransformComponent> transformMapper;

    public ZComparator() {
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public int compare(Entity entityA, Entity entityB) {
        float entityAZPos = transformMapper.get(entityA).position.z;
        float entityBZPos = transformMapper.get(entityB).position.z;

        int res = 0;
        if (entityAZPos > entityBZPos) {
            res = 1;
        } else if (entityAZPos < entityBZPos) {
            res = -1;
        }

        return res;
    }
}
