package com.siegetd.game.models.ecs.utils;

import com.badlogic.ashley.core.Entity;
import com.siegetd.game.models.ecs.components.HitpointComponent;

public class ComponentUpdater {

    public ComponentUpdater() { }

    public void updateHitpointComponent(
            Entity entity,
            int newHitpoints
    ) {
        entity.remove(HitpointComponent.class);
        entity.add(new HitpointComponent(newHitpoints));
    }
}
