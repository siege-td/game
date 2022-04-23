package com.siegetd.game.models.ecs.entities.defender;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.siegetd.game.SiegeTdState;
import com.siegetd.game.models.ecs.components.CharacteristicsComponent;
import com.siegetd.game.models.ecs.components.TextureComponent;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.models.ecs.components.TurretComponent;
import com.siegetd.game.models.ecs.components.Type;
import com.siegetd.game.models.ecs.components.TypeComponent;
import com.siegetd.game.models.ecs.entities.IEntity;

public class MageEntity implements IEntity {

    private Vector2 pos;

    public MageEntity(Vector2 spawnPos) {
        this.pos = spawnPos;
    }

    @Override
    public void create() {
        Entity entity = SiegeTdState.ecsEngine.createEntity();

        Pixmap origMageImg = new Pixmap(Gdx.files.internal("towers/mage.png"));
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
                (pos.x - (pos.x % (SiegeTdState.camera.viewportWidth / TILE_COLUMN))),
                (pos.y - (pos.y % (SiegeTdState.camera.viewportHeight / TILE_ROW)))
        ));
        entity.add(new TextureComponent(new Texture(scaledMageImg)));
        entity.add(new TypeComponent(Type.DEFENDER, Defender.MAGE));
        entity.add(new CharacteristicsComponent(3, 1f, 1));
        entity.add(new TurretComponent());

        origMageImg.dispose();
        scaledMageImg.dispose();

        SiegeTdState.ecsEngine.addEntity(entity);
    }
}
