package com.siegetd.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.models.ecs.entities.TestEntity;
import com.siegetd.game.models.ecs.systems.AnimationSystem;
import com.siegetd.game.models.ecs.systems.MovementSystem;
import com.siegetd.game.models.ecs.systems.RenderingSystem;

public class SiegeTd extends ApplicationAdapter {
	public static final String APP_TITLE = "SiegeTD";
	public static final int APP_HEIGHT = 414 ;
	public static final int APP_WIDTH = 896 ;
	public static final int APP_FPS = 60 ;

	private SpriteBatch batch;
	private RenderingSystem renderingSystem;
	private OrthographicCamera camera;
	private PooledEngine engine;
	private GameStateController gsm;

	@Override
	public void create () {
		batch = new SpriteBatch();

		renderingSystem = new RenderingSystem(batch);

		camera = renderingSystem.getCamera();
		batch.setProjectionMatrix(camera.combined);

		engine = new PooledEngine();

		engine.addSystem(new AnimationSystem());
		engine.addSystem(renderingSystem);
		engine.addSystem(new MovementSystem());

		new TestEntity(engine).create();

		gsm = new GameStateController(this);
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		engine.update(Gdx.graphics.getDeltaTime());

		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
	}

	@Override
	public void dispose () {
		gsm.dispose();
		batch.dispose();
	}

	public SpriteBatch getBatch(){
		return batch;
	}
}