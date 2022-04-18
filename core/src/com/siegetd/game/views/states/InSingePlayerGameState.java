package com.siegetd.game.views.states;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.InputHandler;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.models.ecs.systems.AnimationSystem;
import com.siegetd.game.models.ecs.systems.MovementSystem;
import com.siegetd.game.models.ecs.systems.RenderingSystem;
import com.siegetd.game.models.map.GameMap;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.components.gamestats.GameStats;
import com.siegetd.game.views.components.ingame.InGameGUI;

import java.net.URISyntaxException;

public class InSingePlayerGameState extends GameState {

    // Tilemap fields
    private GameMap gameMap;

    // Ecs fields
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private PooledEngine engine;
    private RenderingSystem renderingSystem;

    // Other fields
    private GameStats gameStats;
    private InputHandler inputHandler;
    private Stage stage;
    private InGameGUI inGameGUI;

    public InSingePlayerGameState(GameStateController gsc) {
        super(gsc);

        batch = new SpriteBatch();

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        this.camera.position.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0);
        this.camera.setToOrtho(false, TILE_COLUMN * TILE_SIZE, TILE_ROW * TILE_SIZE);

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

        this.gameMap = new GameMap(camera);

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        this.inputHandler = new InputHandler(camera, stage, engine);

        this.inGameGUI = new InGameGUI(camera, stage);
    }

    @Override
    public void update(float delta) { }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameMap.render();
        inputHandler.listen();
        engine.update(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() { }
}
