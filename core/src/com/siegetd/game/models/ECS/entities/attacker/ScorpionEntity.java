package com.siegetd.game.models.ecs.entities.attacker;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.siegetd.game.SiegeTdState;
import com.siegetd.game.models.ecs.components.TextureComponent;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.models.ecs.components.TypeComponent;
import com.siegetd.game.models.ecs.components.VelocityComponent;
import com.siegetd.game.models.ecs.entities.IEntity;

public class ScorpionEntity implements IEntity {

    private Vector2 pos;
    private Vector2 speed;

    public ScorpionEntity(Vector2 spawnPos, Vector2 startSpeed) {
        this.pos = spawnPos;
        this.speed = startSpeed;
    }

    @Override
    public void create() {
        Entity entity = SiegeTdState.ecsEngine.createEntity();

        Pixmap origScorpionImg = new Pixmap(Gdx.files.internal("towers/scorpion.png"));
        Pixmap scaledScorpionImg = new Pixmap(
                ((TILE_SIZE * TILE_COLUMN) / TILE_COLUMN),
                ((TILE_SIZE * TILE_ROW) / TILE_ROW),
                origScorpionImg.getFormat()
        );
        scaledScorpionImg.drawPixmap(origScorpionImg,
                0, 0, origScorpionImg.getWidth(), origScorpionImg.getHeight(),
                0, 0, scaledScorpionImg.getWidth(), scaledScorpionImg.getHeight()
        );

        entity.add(new TransformComponent(pos.x, pos.y));
        entity.add(new TextureComponent(new Texture(scaledScorpionImg)));
        entity.add(new VelocityComponent(speed.x, speed.y));
        entity.add(new TypeComponent());

        SiegeTdState.ecsEngine.addEntity(entity);
    }
}
