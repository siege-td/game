package com.siegetd.game.models.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.siegetd.game.models.ecs.components.State;
import com.siegetd.game.models.ecs.components.StateComponent;
import com.siegetd.game.models.ecs.components.TextureComponent;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.models.ecs.components.Type;
import com.siegetd.game.models.ecs.components.TypeComponent;
import com.siegetd.game.models.ecs.components.VelocityComponent;

public class TestEntity implements IEntity {

    private final PooledEngine engine;

    public TestEntity(PooledEngine engine) {
        this.engine = engine;
    }

    @Override
    public void create() {
        Entity entity = engine.createEntity();

        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        StateComponent stateComponent = engine.createComponent(StateComponent.class);
        TypeComponent typeComponent = engine.createComponent(TypeComponent.class);
        VelocityComponent velocityComponent = engine.createComponent(VelocityComponent.class);

        transformComponent.position.set(10f, 10f, 0f);
        textureComponent.region = new TextureRegion(new Texture("badlogic.jpg"));
        typeComponent.type = Type.DEFENDER;
        stateComponent.setState(State.NORMAL);
        velocityComponent.xSpeed = 1f;
        velocityComponent.ySpeed = 0.1f;

        entity.add(transformComponent);
        entity.add(textureComponent);
        entity.add(stateComponent);
        entity.add(typeComponent);
        entity.add(velocityComponent);

        engine.addEntity(entity);
    }
}
