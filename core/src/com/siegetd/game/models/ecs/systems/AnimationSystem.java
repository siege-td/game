package com.siegetd.game.models.ECS.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.siegetd.game.models.ECS.components.AnimationComponent;
import com.siegetd.game.models.ECS.components.StateComponent;
import com.siegetd.game.models.ECS.components.TextureComponent;

public class AnimationSystem extends IteratingSystem {

    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<AnimationComponent> animationMapper;
    private ComponentMapper<StateComponent> stateMapper;

    public AnimationSystem() {
        super(Family.all(
                TextureComponent.class,
                AnimationComponent.class,
                StateComponent.class
        ).get());

        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        animationMapper = ComponentMapper.getFor(AnimationComponent.class);
        stateMapper = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animationComponent = animationMapper.get(entity);
        StateComponent stateComponent = stateMapper.get(entity);

        if (animationComponent.animations.containsKey(stateComponent.getState().ordinal())) {
            TextureComponent textureComponent = textureMapper.get(entity);
            textureComponent.region = animationComponent.animations
                    .get(stateComponent.getState().ordinal())
                    .getKeyFrame(stateComponent.time, stateComponent.isLooping).region;
        }

        stateComponent.time += deltaTime;
    }
}
