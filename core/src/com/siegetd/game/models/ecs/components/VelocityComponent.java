package com.siegetd.game.models.ecs.components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {
    public float xSpeed;
    public float ySpeed;

    public VelocityComponent(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
}
