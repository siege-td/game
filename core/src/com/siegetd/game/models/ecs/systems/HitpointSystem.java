package com.siegetd.game.models.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.siegetd.game.SiegeTdState;
import com.siegetd.game.controllers.ScoreController;
import com.siegetd.game.models.ecs.components.HitpointComponent;

public class HitpointSystem extends IteratingSystem {
    public HitpointSystem() {
        super(Family.all(HitpointComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        int hitpoints = entity.getComponent(HitpointComponent.class).getHitpoints();
        if(hitpoints <= 0){
            SiegeTdState.ecsEngine.removeEntity(entity);
            Sound deathSound = Gdx.audio.newSound(Gdx.files.internal("sounds/DeathSound.mp3"));
            deathSound.play();
            ScoreController.getInstance().addCurrency(5);
        }
    }
}
