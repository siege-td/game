package com.siegetd.game.models.ecs.systems;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.siegetd.game.EngineState;
import com.siegetd.game.models.ecs.components.AttackerComponent;
import com.siegetd.game.models.ecs.components.BulletComponent;
import com.siegetd.game.models.ecs.components.TransformComponent;

public class CollisionSystem extends EntitySystem{
    private ImmutableArray<Entity> bullets;
    private ImmutableArray<Entity> attackers;

    private ComponentMapper<TransformComponent> transformMapper;

    public CollisionSystem() {
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        bullets = EngineState.ecsEngine.getEntitiesFor(
                Family.all(
                        BulletComponent.class
                ).get()
        );

        attackers = EngineState.ecsEngine.getEntitiesFor(
                Family.all(
                        AttackerComponent.class
                ).get()
        );

        checkForLaserCollision();
    }

    private void checkForLaserCollision() {
        for (Entity bullet : bullets) {
            TransformComponent bulletPosition = transformMapper.get(bullet);
            for (Entity attacker : attackers) {
                TransformComponent attackerPosition = transformMapper.get(attacker);
                Rectangle attackerHitBox = new Rectangle((int) attackerPosition.position.x - (TILE_SIZE / 2), (int) attackerPosition.position.y - (TILE_SIZE / 2), TILE_SIZE / 2 , TILE_SIZE / 2);
                if(Intersector.overlaps(new Circle((int) bulletPosition.position.x, (int) bulletPosition.position.x, 2), attackerHitBox)){
                    getEngine().removeEntity(bullet);
                }
            }
        }
    }
}
