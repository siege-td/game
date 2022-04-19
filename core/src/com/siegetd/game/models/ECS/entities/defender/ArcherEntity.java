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
import com.badlogic.gdx.physics.box2d.World;
import com.siegetd.game.models.ECS.components.TextureComponent;
import com.siegetd.game.models.ECS.components.TransformComponent;
import com.siegetd.game.models.ECS.components.Type;
import com.siegetd.game.models.ECS.components.TypeComponent;
import com.siegetd.game.models.ECS.entities.IEntity;

import java.awt.Rectangle;
public class ArcherEntity implements IEntity {

    private final PooledEngine engine;
    private Vector2 pos;
    private OrthographicCamera camera;
    private int tileRadius = 3;

    public ArcherEntity(PooledEngine engine, Vector2 spawnPos, OrthographicCamera camera) {
        this.engine = engine;
        this.pos = spawnPos;
        this.camera = camera;
    }

    public void listenForEnemies(){
        for (Entity entity : engine.getEntities()) {
            //Check if attacker
            if(entity.getComponent(TypeComponent.class).type.equals(Type.ATTACKER)){
                //Check if within radius
                Vector2 entityPos = entity.getComponent(TransformComponent.class).position;

                Rectangle entityRect = new Rectangle((int) entityPos.x,(int) entityPos.y, TILE_SIZE, TILE_SIZE);
                Rectangle towerRect = new Rectangle(((int) pos.x) - ((tileRadius / 2) * TILE_SIZE), ((int) pos.y) - ((tileRadius / 2) * TILE_SIZE), TILE_SIZE * tileRadius, TILE_SIZE * tileRadius);

                if(entityRect.intersects(towerRect)){
                    System.out.println("SHOOT!");
                }
            }
        }
    }

    public void create() {
        Entity entity = engine.createEntity();

        Pixmap origMageImg = new Pixmap(Gdx.files.internal("towers/archer.png"));
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
        entity.add(new TypeComponent(Type.DEFENDER));

        origMageImg.dispose();
        scaledMageImg.dispose();

        engine.addEntity(entity);
    }
}
