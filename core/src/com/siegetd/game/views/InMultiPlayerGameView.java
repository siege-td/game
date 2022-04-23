package com.siegetd.game.views;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.EngineState;
import com.siegetd.game.controllers.GameViewController;
import com.siegetd.game.controllers.InputController;
import com.siegetd.game.models.ecs.systems.AnimationSystem;
import com.siegetd.game.models.ecs.systems.MovementSystem;
import com.siegetd.game.models.ecs.systems.RenderingSystem;
import com.siegetd.game.models.map.GameMap;
import com.siegetd.game.views.components.ingame.InGameGUI;

import java.net.URISyntaxException;

public class InMultiPlayerGameView extends GameView {

    // Tilemap fields
    private GameMap gameMap;

    // Ecs fields
    private OrthographicCamera camera;
    private PooledEngine engine;
    private RenderingSystem renderingSystem;

    private InputController inputController;

    public InMultiPlayerGameView(GameViewController gsc) {
        super(gsc);

        EngineState.batch = new SpriteBatch();

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        this.camera.position.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0);
        this.camera.setToOrtho(false, TILE_COLUMN * TILE_SIZE, TILE_ROW * TILE_SIZE);

        EngineState.camera = camera;

        engine = new PooledEngine();

        try {
            renderingSystem = new RenderingSystem();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        this.gameMap = new GameMap(camera);

        EngineState.gameMap = this.gameMap;

        engine.addSystem(new AnimationSystem());
        engine.addSystem(renderingSystem);
        engine.addSystem(new MovementSystem());

        EngineState.ecsEngine = engine;

        EngineState.stage = new Stage();
        Gdx.input.setInputProcessor(EngineState.stage);

        this.inputController = new InputController();

        new InGameGUI();
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameMap.render();
        inputController.listen();
        engine.update(Gdx.graphics.getDeltaTime());
        EngineState.stage.draw();
    }

    @Override
    public void dispose() { }
}
