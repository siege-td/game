package com.siegetd.game.models.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class VelocityComponent implements Component {
    public final float xSpeed = 0f;
    public final float ySpeed = 0f;
    public Vector2 direction = new Vector2();
}
