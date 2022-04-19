package com.siegetd.game.models.ECS.entities.defender;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.siegetd.game.models.ECS.components.TextureComponent;
import com.siegetd.game.models.ECS.components.TransformComponent;
import com.siegetd.game.models.ECS.entities.IEntity;

public class ZappEntity implements IEntity {

    private final PooledEngine engine;
    private Vector2 pos;
    private OrthographicCamera camera;

    public ZappEntity(PooledEngine engine, Vector2 spawnPos, OrthographicCamera camera) {
        this.engine = engine;
        this.pos = spawnPos;
        this.camera = camera;
    }

    @Override
    public void create() {
        Entity entity = engine.createEntity();

        Pixmap origMageImg = new Pixmap(Gdx.files.internal("towers/zapp.png"));
        Pixmap scaledMageImg = new Pixmap(
                ((TILE_SIZE * TILE_COLUMN) / TILE_COLUMN) * 2,
                ((TILE_SIZE * TILE_ROW) / TILE_ROW) * 2,
                origMageImg.getFormat()
        );
        scaledMageImg.drawPixmap(origMageImg,
                0, 0, origMageImg.getWidth(), origMageImg.getHeight(),
                0, 0, scaledMageImg.getWidth(), scaledMageImg.getHeight()
        );

        entity.add(new TransformComponent(
                (pos.x - (pos.x % (camera.viewportWidth / TILE_COLUMN))),
                (pos.y - (pos.y % (camera.viewportHeight / TILE_ROW)))
        ));
        entity.add(new TextureComponent(new Texture(scaledMageImg)));

        origMageImg.dispose();
        scaledMageImg.dispose();

        engine.addEntity(entity);
    }
}
