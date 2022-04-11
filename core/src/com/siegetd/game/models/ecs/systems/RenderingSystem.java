package com.siegetd.game.models.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.siegetd.game.models.ecs.components.TextureComponent;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.views.components.GameStats;

import java.net.URISyntaxException;

public class RenderingSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<TransformComponent> transformMapper;

    private GameStats gameStats;

    public RenderingSystem(SpriteBatch batch, OrthographicCamera camera) throws URISyntaxException {
        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);

        this.camera = camera;
        this.batch = batch;
    }

    @Override
    public void addedToEngine (Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TransformComponent.class, TextureComponent.class).get());
    }

    @Override
    public void removedFromEngine (Engine engine) {

    }

    @Override
    public void update(float deltaTime) {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        try {
            gameStats = new GameStats(batch);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        gameStats.updateStats();


        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            TextureComponent textureComponent = textureMapper.get(entity);
            TransformComponent transformComponent = transformMapper.get(entity);

            float textureWidth = textureComponent.region.getRegionWidth();
            float textureHeight = textureComponent.region.getRegionHeight();

            float textureOriginX = textureWidth / 2f;
            float textureOriginY = textureHeight / 2f;

            batch.draw(
                    textureComponent.region,
                    transformComponent.position.x,
                    transformComponent.position.y,
                    textureWidth,
                    textureHeight,
                    textureOriginX,
                    textureOriginY,
                    1f,
                    1f,
                    0f
            );
        }

        batch.end();
    }
}
