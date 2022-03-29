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

import org.json.JSONException;

import java.net.URISyntaxException;

import io.socket.client.Socket;

public class SiegeTd extends ApplicationAdapter {

	private SpriteBatch batch;
	private RenderingSystem renderingSystem;
	private OrthographicCamera camera;
	private PooledEngine engine;

	private SocketConnection socketConnection;
	private Socket socket;

	private ComponentUpdater componentUpdater;

	public SiegeTd() {
		try {
			socketConnection = new SocketConnection();
			this.socket = socketConnection.getSocket();
		} catch (URISyntaxException error) {
			System.out.println(error);
		}
	}

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

		//This has to be initialized after all other entities, i think

		componentUpdater = new ComponentUpdater();


		//socket.connected();
		//socket.emit("new_lobby", 1);
		//socket.emit("close_lobby", 1);

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
		try {
			componentUpdater.updateCurrencyComponent(engine.getEntities().get(0), 10);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		batch.dispose();
	}

	public SpriteBatch getBatch(){
		return batch;
	}
}