package com.siegetd.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.siegetd.game.controllers.GameStateController;

public class SiegeTd extends ApplicationAdapter {
	public static final String APP_TITLE = "SiegeTD";
	public static final int APP_HEIGHT = 414 ;
	public static final int APP_WIDTH = 896 ;
	public static final int APP_FPS = 60 ;

	private SpriteBatch batch;
	private GameStateController gsm;

	@Override
	public void create () {
		batch = new SpriteBatch();

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