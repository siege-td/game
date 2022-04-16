package com.siegetd.game.models.ecs.entities;

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

public class MageEntity implements IEntity {

    private final PooledEngine engine;
    private Vector2 pos;

    public MageEntity(PooledEngine engine, Vector2 spawnPos) {
        this.engine = engine;
        this.pos = spawnPos;
    }

    @Override
    public void create() {
        Entity entity = engine.createEntity();

        Pixmap origMageImg = new Pixmap(Gdx.files.internal("towers/mage.png"));
        Pixmap scaledMageImg = new Pixmap(
                (TILE_SIZE * TILE_COLUMN) / TILE_COLUMN,
                (TILE_SIZE * TILE_ROW) / TILE_ROW,
                origMageImg.getFormat()
        );
        scaledMageImg.drawPixmap(origMageImg,
                0, 0, origMageImg.getWidth(), origMageImg.getHeight(),
                0, 0, scaledMageImg.getWidth(), scaledMageImg.getHeight()
        );

        entity.add(new TransformComponent(pos.x, pos.y));
        entity.add(new TextureComponent(new Texture(scaledMageImg)));

        origMageImg.dispose();
        scaledMageImg.dispose();

        engine.addEntity(entity);
    }
}
