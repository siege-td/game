package com.siegetd.game.models.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.siegetd.game.models.ecs.components.CurrencyComponent;
import com.siegetd.game.models.ecs.components.HitpointComponent;
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

        StateComponent stateComponent = engine.createComponent(StateComponent.class);
        TypeComponent typeComponent = engine.createComponent(TypeComponent.class);
        VelocityComponent velocityComponent = engine.createComponent(VelocityComponent.class);

        typeComponent.type = Type.DEFENDER;
        stateComponent.setState(State.NORMAL);
        velocityComponent.xSpeed = 5f;
        velocityComponent.ySpeed = 5f;

        entity.add(new TransformComponent(10f, 10f));
        entity.add(new TextureComponent(new Texture("badlogic.jpg")));
        entity.add(stateComponent);
        entity.add(typeComponent);
        entity.add(velocityComponent);
        entity.add(new HitpointComponent(100));
        entity.add(new CurrencyComponent(0));

        engine.addEntity(entity);
    }
}
