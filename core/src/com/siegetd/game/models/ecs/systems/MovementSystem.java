package com.siegetd.game.models.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.siegetd.game.EngineState;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.models.ecs.components.VelocityComponent;
import com.siegetd.game.models.map.tile.MovableTile;
import com.siegetd.game.singletons.ScoreHandler;

import java.util.LinkedList;
import java.util.List;

public class MovementSystem extends IteratingSystem {
    private ComponentMapper<TransformComponent> transformMapper;
    private ComponentMapper<VelocityComponent> velocityMapper;
    private VelocityComponent velocityComponent;
    public MovementSystem() {
        super(Family.all(TransformComponent.class, VelocityComponent.class).get());

        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = transformMapper.get(entity);
        this.velocityComponent = velocityMapper.get(entity);

        updateMovement((int) transformComponent.position.x, (int) transformComponent.position.y, entity);

        transformComponent.position.x += velocityComponent.xSpeed * deltaTime;
        transformComponent.position.y += velocityComponent.ySpeed * deltaTime;
    }


    public void updateMovement(float currentX, float currentY, Entity curEntity) {
        float xDis = this.velocityComponent.nextX - currentX;
        float yDis = this.velocityComponent.nextY - currentY;

        if (xDis <= 1 && yDis <= 1) {
            this.velocityComponent.pathIndex++;
            getNextPos(curEntity);
        } else {
            this.velocityComponent.xSpeed = this.velocityComponent.origXSpeed * Integer.signum((int) xDis);
            this.velocityComponent.ySpeed = this.velocityComponent.origYSpeed * Integer.signum((int) yDis);
        }
    }

    private void getNextPos(Entity curEntity) {
        if (this.velocityComponent.pathIndex >= this.velocityComponent.path.size()) {
            EngineState.ecsEngine.removeEntity(curEntity);
            ScoreHandler.getInstance().setHealth(ScoreHandler.getInstance().getHealth()-10);
            System.out.println(ScoreHandler.getInstance().getHealth());
            return;
        }

        this.velocityComponent.nextX = this.velocityComponent.path.get(this.velocityComponent.pathIndex).getX() + 50;
        this.velocityComponent.nextY = this.velocityComponent.path.get(this.velocityComponent.pathIndex).getY() + 60;
    }
}
