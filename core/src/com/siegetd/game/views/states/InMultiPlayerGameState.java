package com.siegetd.game.views.states;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.siegetd.game.Globals;
import com.siegetd.game.api.SocketConnection;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.models.ecs.entities.TestEntity;
import com.siegetd.game.models.ecs.systems.AnimationSystem;
import com.siegetd.game.models.ecs.systems.MovementSystem;
import com.siegetd.game.models.ecs.systems.RenderingSystem;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.components.gamestats.GameStats;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class InMultiPlayerGameState extends GameState {

    private SpriteBatch batch;
    private RenderingSystem renderingSystem;
    private OrthographicCamera camera;
    private PooledEngine engine;

    private GameStats gameStats;

    public InMultiPlayerGameState(GameStateController gsc) {
        super(gsc);

        batch = new SpriteBatch();

        this.camera = new OrthographicCamera(640, 480);
        this.camera.position.set(320, 240, 0);
        this.camera.update();

        engine = new PooledEngine();

        try {
            gameStats = new GameStats(this.batch);
            renderingSystem = new RenderingSystem(batch, this.camera, this.gameStats);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        engine.addSystem(new AnimationSystem());
        engine.addSystem(renderingSystem);
        engine.addSystem(new MovementSystem());

        new TestEntity(engine).create();
        new TestEntity(engine).create();

        doAfter3sec();
    }

    @Override
    public void update(float delta) { }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(Gdx.graphics.getDeltaTime());
        //if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }

    @Override
    public void dispose() { }

    private void doAfter3sec() {
        Timer timer = new Timer();
        Timer.Task task = timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                try {
                    JSONObject object = new JSONObject();
                    object.put("pin", Globals.pin);
                    object.put("playerName", SocketConnection.getInstance().getSocket().id());
                    object.put("hitpoints", 45);
                    object.put("currency", 7878);

                    SocketConnection.getInstance().getSocket().emit("update_game_data", object);
                } catch (JSONException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }, 3);
    }
}
