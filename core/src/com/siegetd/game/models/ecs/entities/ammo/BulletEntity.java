package com.siegetd.game.models.ecs.entities.ammo;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.siegetd.game.EngineState;
import com.siegetd.game.models.ecs.components.BulletComponent;
import com.siegetd.game.models.ecs.components.TextureComponent;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.models.ecs.components.VelocityComponent;
import com.siegetd.game.models.ecs.entities.IEntity;

public class BulletEntity implements IEntity {

    private Texture texture;
    private float x;
    private float y;
    private final float BULLET_SPEED = 600;


    public BulletEntity(Texture texture, int x, int y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
    }

    @Override
    public void create() {
        Entity entity = EngineState.ecsEngine.createEntity();

        entity.add(new BulletComponent());
        entity.add(new TransformComponent(x, y));
        entity.add(new TextureComponent(texture));
        entity.add(new VelocityComponent(BULLET_SPEED, BULLET_SPEED));

        EngineState.ecsEngine.addEntity(entity);
    }
}
