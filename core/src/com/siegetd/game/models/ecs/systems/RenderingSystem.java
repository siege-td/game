package com.siegetd.game.models.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.siegetd.game.models.ecs.components.TextureComponent;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.models.ecs.utils.RenderUtils;
import com.siegetd.game.models.ecs.utils.ZComparator;

import java.util.Comparator;

public class RenderingSystem extends SortedIteratingSystem {

    private SpriteBatch batch;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private OrthographicCamera camera;

    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<TransformComponent> transformMapper;

    private RenderUtils renderUtils;

    public RenderingSystem(SpriteBatch batch) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get(), new ZComparator());

        renderUtils = new RenderUtils();

        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);

        renderQueue = new Array<>();

        this.batch = batch;

        camera = new OrthographicCamera(renderUtils.getFrustumWidth(), renderUtils.getFrustumHeight());
        camera.position.set(renderUtils.getFrustumWidth() / 2f, renderUtils.getFrustumHeight() / 2f, 0);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        renderQueue.sort(comparator);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();
        batch.begin();

        for (Entity entity : renderQueue) {
            TextureComponent textureComponent = textureMapper.get(entity);
            TransformComponent transformComponent = transformMapper.get(entity);

            if (textureComponent.region == null || transformComponent.isHidden) {
                continue;
            }

            float textureWidth = textureComponent.region.getRegionWidth();
            float textureHeight = textureComponent.region.getRegionHeight();

            float textureOriginX = textureWidth / 2f;
            float textureOriginY = textureHeight / 2f;

            batch.draw(
                    textureComponent.region,
                    transformComponent.position.x - textureOriginX,
                    transformComponent.position.y - textureOriginY,
                    textureOriginX,
                    textureOriginY,
                    textureWidth,
                    textureHeight,
                    renderUtils.convertPixelsToMeters(transformComponent.scale.x),
                    renderUtils.convertPixelsToMeters(transformComponent.scale.y),
                    transformComponent.rotation
            );

            batch.end();
            renderQueue.clear();
        }
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
