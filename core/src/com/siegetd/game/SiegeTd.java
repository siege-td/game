package com.siegetd.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.siegetd.game.controllers.GameStateController;

public class SiegeTd extends ApplicationAdapter {

	public static final float SCALE = 2.0f;

	private GameStateController gsm;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateController(this);
	}

	@Override
	public void render () {
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();

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