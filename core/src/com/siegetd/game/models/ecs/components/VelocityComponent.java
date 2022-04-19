package com.siegetd.game.models.ecs.components;

import com.badlogic.ashley.core.Component;
import com.siegetd.game.Globals;
import com.siegetd.game.models.map.tile.MovableTile;

import java.util.List;

public class VelocityComponent implements Component {

    public float xSpeed;
    public float ySpeed;

    private List<MovableTile> path;
    private int pathIndex;
    private float nextX;
    private float nextY;

    public VelocityComponent(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

        this.pathIndex = 0;
        this.path = Globals.gameMap.getMovableTiles();

        this.getNextPos();
    }

    public void updateMovement(float currentX, float currentY) {
        float xDis = this.nextX - currentX;
        float yDis = this.nextY - currentY;

        if (xDis == 0 && yDis == 0) {
            pathIndex++;
            getNextPos();
        } else {
            this.xSpeed = 256 * Integer.signum((int) xDis);
            this.ySpeed = 256 * Integer.signum((int) yDis);
        }
    }

    private void getNextPos() {
        if (pathIndex > path.size()) {
            this.xSpeed = 0;
            this.ySpeed = 0;
            return;
        }

        this.nextX = path.get(pathIndex).getX();
        this.nextY = path.get(pathIndex).getY();
    }
}
