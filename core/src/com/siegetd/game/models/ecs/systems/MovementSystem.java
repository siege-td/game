package com.siegetd.game.models.ECS.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.siegetd.game.models.ECS.components.TransformComponent;
import com.siegetd.game.models.ECS.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {
    private ComponentMapper<TransformComponent> transformMapper;
    private ComponentMapper<VelocityComponent> velocityMapper;

    public MovementSystem() {
        super(Family.all(TransformComponent.class, VelocityComponent.class).get());

        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = transformMapper.get(entity);
        VelocityComponent velocityComponent = velocityMapper.get(entity);

        transformComponent.position.x += velocityComponent.xSpeed * deltaTime;
        transformComponent.position.y += velocityComponent.ySpeed * deltaTime;
    }
}
