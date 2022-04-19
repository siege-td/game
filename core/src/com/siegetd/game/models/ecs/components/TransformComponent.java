package com.siegetd.game.models.ECS.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component {
    public Vector2 position;
    public final Vector2 scale = new Vector2(1f, 1f);
    public float rotation = 0f;
    public boolean isHidden = false;

    public TransformComponent(float xPos, float yPos) {
        this.position = new Vector2(xPos, yPos);
    }
}
