package com.siegetd.game.models.ECS.entities.ammo;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.siegetd.game.models.ECS.components.TextureComponent;
import com.siegetd.game.models.ECS.components.TransformComponent;
import com.siegetd.game.models.ECS.components.VelocityComponent;
import com.siegetd.game.models.ECS.entities.IEntity;

public class AmmoEntity implements IEntity {

    private final PooledEngine engine;
    private Vector2 from;
    private Vector2 to;

    public AmmoEntity(PooledEngine engine, Vector2 from, Vector2 to) {
        this.engine = engine;
        this.from = from;
        this.to = to;
    }

    public void updateTarget(Vector2 newToPosition){
        this.to = newToPosition;
    }

    @Override
    public void create() {
        Entity entity = engine.createEntity();

        Pixmap origScorpionImg = new Pixmap(Gdx.files.internal("ammo/stone_small.png"));
        Pixmap scaledScorpionImg = new Pixmap(
                ((TILE_SIZE * TILE_COLUMN) / TILE_COLUMN) * 1,
                ((TILE_SIZE * TILE_ROW) / TILE_ROW) * 1,
                origScorpionImg.getFormat()
        );
        scaledScorpionImg.drawPixmap(origScorpionImg,
                0, 0, origScorpionImg.getWidth(), origScorpionImg.getHeight(),
                0, 0, scaledScorpionImg.getWidth(), scaledScorpionImg.getHeight()
        );

        entity.add(new TransformComponent(from.x, from.y));
        entity.add(new TextureComponent(new Texture(scaledScorpionImg)));
        entity.add(new VelocityComponent(to.x, to.y));

        engine.addEntity(entity);
    }
}
