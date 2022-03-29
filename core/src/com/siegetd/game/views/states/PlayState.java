package com.siegetd.game.views.states;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.models.ecs.entities.TestEntity;
import com.siegetd.game.models.ecs.systems.AnimationSystem;
import com.siegetd.game.models.ecs.systems.MovementSystem;
import com.siegetd.game.models.ecs.systems.RenderingSystem;
import com.siegetd.game.models.map.GameMap;
import com.siegetd.game.views.GameState;

public class PlayState extends GameState{
    private GameMap gameMap;
    private RenderingSystem renderingSystem;
    private OrthographicCamera camera;
    private PooledEngine engine;

    public PlayState(GameStateController gsc){
        super(gsc);

        renderingSystem = new RenderingSystem(batch);

        camera = renderingSystem.getCamera();
        batch.setProjectionMatrix(camera.combined);

        engine = new PooledEngine();

        engine.addSystem(new AnimationSystem());
        engine.addSystem(renderingSystem);
        engine.addSystem(new MovementSystem());

        gameMap = new GameMap(camera);
    }

    @Override
    public void update(float delta) {
        gameMap.update();
    }

    @Override
    public void render() {
        gameMap.render();
    }

    @Override
    public void dispose() {
    }
}
