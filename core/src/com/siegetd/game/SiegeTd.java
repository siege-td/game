package com.siegetd.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.siegetd.game.api.SocketConnection;
import com.siegetd.game.models.ecs.entities.TestEntity;
import com.siegetd.game.models.ecs.systems.AnimationSystem;
import com.siegetd.game.models.ecs.systems.MovementSystem;
import com.siegetd.game.models.ecs.systems.RenderingSystem;
import com.siegetd.game.models.ecs.utils.ComponentUpdater;

import java.net.URISyntaxException;

import io.socket.client.Socket;

public class SiegeTd extends ApplicationAdapter {

	private SpriteBatch batch;
	private RenderingSystem renderingSystem;
	private OrthographicCamera camera;
	private PooledEngine engine;
	private GameStateController gsm;

	private SocketConnection socketConnection;
	private Socket socket;

	private ComponentUpdater componentUpdater;

	public SiegeTd() {
		try {
			socketConnection = SocketConnection.getInstance();
			this.socket = socketConnection.getSocket();
		} catch (URISyntaxException error) {
			System.out.println(error);
		}

	}

	@Override
	public void create () {
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

		gsm = new GameStateController(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
		//engine.update(Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch(){
		return batch;
	}
}