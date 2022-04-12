package com.siegetd.game.views.states;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.models.ecs.entities.TestEntity;
import com.siegetd.game.models.ecs.systems.AnimationSystem;
import com.siegetd.game.models.ecs.systems.MovementSystem;
import com.siegetd.game.models.ecs.systems.RenderingSystem;
import com.siegetd.game.views.GameState;

import java.net.URISyntaxException;

public class InMultiPlayerGameState extends GameState {

    private SpriteBatch batch;
    private RenderingSystem renderingSystem;
    private OrthographicCamera camera;
    private PooledEngine engine;

    public InMultiPlayerGameState(GameStateController gsc) {
        super(gsc);

        batch = new SpriteBatch();

        this.camera = new OrthographicCamera(640, 480);
        this.camera.position.set(320, 240, 0);
        this.camera.update();

        engine = new PooledEngine();

        try {
            renderingSystem = new RenderingSystem(batch, this.camera);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        engine.addSystem(new AnimationSystem());
        engine.addSystem(renderingSystem);
        engine.addSystem(new MovementSystem());

        new TestEntity(engine).create();
        new TestEntity(engine).create();
    }

    @Override
    public void update(float delta) { }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(Gdx.graphics.getDeltaTime());
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }

    @Override
    public void dispose() { }
}
