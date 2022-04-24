package com.siegetd.game.models.ecs.entities.ammo;

import com.badlogic.ashley.core.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.siegetd.game.SiegeTdState;
import com.siegetd.game.models.ecs.components.BulletComponent;
import com.siegetd.game.models.ecs.components.TextureComponent;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.models.ecs.components.Type;
import com.siegetd.game.models.ecs.components.TypeComponent;
import com.siegetd.game.models.ecs.components.VelocityComponent;

public class BulletEntity extends Entity {

    private Texture texture;
    private float x;
    private float y;
    private float xDir;
    private float yDir;
    private final float BULLET_SPEED = 1000;
    private int bulletDamage;


    public BulletEntity(Texture texture, int x, int y, float xDir, float yDir, int dmg) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.bulletDamage = dmg;
        this.xDir = xDir*BULLET_SPEED;
        this.yDir = yDir*BULLET_SPEED;

        create();
    }

    public void create() {
        Entity entity = SiegeTdState.ecsEngine.createEntity();

        entity.add(new TypeComponent(Type.PROJECTILE));
        entity.add(new BulletComponent(bulletDamage));
        entity.add(new TransformComponent(x, y));
        entity.add(new TextureComponent(texture));

        entity.add(new VelocityComponent((int) xDir, (int) yDir));

        SiegeTdState.ecsEngine.addEntity(entity);
    }
}
