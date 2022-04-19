package com.siegetd.game.models.ecs.entities.attacker;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.siegetd.game.models.ecs.components.TextureComponent;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.models.ecs.components.VelocityComponent;
import com.siegetd.game.models.ecs.entities.IEntity;

public class OgreEntity implements IEntity {

    private final PooledEngine engine;
    private Vector2 pos;
    private Vector2 speed;

    public OgreEntity(PooledEngine engine, Vector2 spawnPos, Vector2 startSpeed) {
        this.engine = engine;
        this.pos = spawnPos;
        this.speed = startSpeed;
    }

    @Override
    public void create() {
        Entity entity = engine.createEntity();

        Pixmap origOgreImg = new Pixmap(Gdx.files.internal("towers/ogre.png"));
        Pixmap scaledOgreImg = new Pixmap(
                ((TILE_SIZE * TILE_COLUMN) / TILE_COLUMN),
                ((TILE_SIZE * TILE_ROW) / TILE_ROW),
                origOgreImg.getFormat()
        );
        scaledOgreImg.drawPixmap(origOgreImg,
                0, 0, origOgreImg.getWidth(), origOgreImg.getHeight(),
                0, 0, scaledOgreImg.getWidth(), scaledOgreImg.getHeight()
        );

        entity.add(new TransformComponent(pos.x, pos.y));
        entity.add(new TextureComponent(new Texture(scaledOgreImg)));
        entity.add(new VelocityComponent(speed.x, speed.y));

        engine.addEntity(entity);
    }
}
