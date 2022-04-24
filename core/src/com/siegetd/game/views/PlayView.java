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
import com.siegetd.game.SiegeTdState;
import com.siegetd.game.controllers.GameViewController;
import com.siegetd.game.controllers.InputController;
import com.siegetd.game.controllers.LevelController;
import com.siegetd.game.controllers.ScoreController;
import com.siegetd.game.models.ecs.systems.CollisionSystem;
import com.siegetd.game.models.ecs.systems.DefenderAiSystem;
import com.siegetd.game.models.ecs.systems.HitpointSystem;
import com.siegetd.game.models.ecs.systems.MovementSystem;
import com.siegetd.game.models.ecs.systems.RenderingSystem;
import com.siegetd.game.models.map.GameMap;

import java.net.URISyntaxException;

public class PlayView extends GameView {

    // Tilemap fields
    private GameMap gameMap;

    // Ecs fields
    private OrthographicCamera camera;
    private PooledEngine engine;
    private RenderingSystem renderingSystem;

    // Other fields
    private InputController inputController;
    private LevelController levelController;

    public PlayView(GameViewController gsc) {
        super(gsc);

        SiegeTdState.batch = new SpriteBatch();

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        this.camera.position.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0);
        this.camera.setToOrtho(false, TILE_COLUMN * TILE_SIZE, TILE_ROW * TILE_SIZE);

        SiegeTdState.camera = camera;

        engine = new PooledEngine();

        try {
            renderingSystem = new RenderingSystem();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        this.gameMap = new GameMap(camera);

        SiegeTdState.gameMap = this.gameMap;

        engine.addSystem(renderingSystem);
        engine.addSystem(new MovementSystem());
        engine.addSystem(new DefenderAiSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new HitpointSystem());

        SiegeTdState.ecsEngine = engine;

        SiegeTdState.stage = new Stage();
        Gdx.input.setInputProcessor(SiegeTdState.stage);

        this.inputController = new InputController();
        this.levelController = new LevelController(1);

    }

    @Override
    public void update(float delta) {
        levelController.isRoundFinished();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SiegeTdState.gameMap.render();
        inputController.listen();
        SiegeTdState.ecsEngine.update(Gdx.graphics.getDeltaTime());
        SiegeTdState.stage.draw();
    }

    @Override
    public void dispose() { }

}
