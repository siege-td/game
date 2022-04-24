package com.siegetd.game.models.ecs.components;

import com.badlogic.ashley.core.Component;

public class BulletComponent implements Component {
    public int bulletDamage = 0;

    public BulletComponent(int damage) {
        this.bulletDamage = damage;
    }
}
