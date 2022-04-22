package com.siegetd.game.models.ecs.components;

import com.badlogic.ashley.core.Component;
import com.siegetd.game.EngineState;
import com.siegetd.game.models.map.tile.MovableTile;

import java.util.List;

public class VelocityComponent implements Component {

    public float xSpeed;
    public float ySpeed;

    public float origXSpeed;
    public float origYSpeed;

    public List<MovableTile> path;
    public int pathIndex;
    public float nextX;
    public float nextY;

    public VelocityComponent(float xSpeed, float ySpeed) {
        this.origXSpeed = xSpeed;
        this.origYSpeed = ySpeed;

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

        this.pathIndex = 0;
        this.path = EngineState.gameMap.getMovableTiles();
    }
}
