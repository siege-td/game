package com.siegetd.game.models.ecs.components;

import com.badlogic.ashley.core.Component;
import com.siegetd.game.EngineState;
import com.siegetd.game.models.map.tile.MovableTile;

import java.util.List;

public class VelocityComponent implements Component {

    public float xSpeed;
    public float ySpeed;

    private float origXSpeed;
    private float origYSpeed;

    private List<MovableTile> path;
    private int pathIndex;
    private float nextX;
    private float nextY;

    public VelocityComponent(float xSpeed, float ySpeed) {
        this.origXSpeed = xSpeed;
        this.origYSpeed = ySpeed;

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

        this.pathIndex = 0;
        this.path = EngineState.gameMap.getMovableTiles();
        this.getNextPos();
    }

    public void updateMovement(float currentX, float currentY) {
        float xDis = this.nextX - currentX;
        float yDis = this.nextY - currentY;

        if (xDis <= 5 && yDis <= 5) {
            pathIndex++;
            getNextPos();
        } else {
            this.xSpeed = this.origXSpeed * Integer.signum((int) xDis);
            this.ySpeed = this.origYSpeed * Integer.signum((int) yDis);
        }
    }

    private void getNextPos() {
        if (pathIndex >= path.size()) {
            this.xSpeed = 0;
            this.ySpeed = 0;
            return;
        }

        this.nextX = path.get(pathIndex).getX() + 50;
        this.nextY = path.get(pathIndex).getY() + 60;
    }
}
