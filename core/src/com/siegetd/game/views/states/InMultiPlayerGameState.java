package com.siegetd.game.views.states;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.models.ecs.systems.AnimationSystem;
import com.siegetd.game.models.ecs.systems.MovementSystem;
import com.siegetd.game.models.ecs.systems.RenderingSystem;
import com.siegetd.game.models.map.GameMap;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.components.gamestats.GameStats;

import java.net.URISyntaxException;

public class InMultiPlayerGameState extends GameState {

    // Tilemap fields
    private GameMap gameMap;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    // Ecs fields
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private PooledEngine engine;
    private RenderingSystem renderingSystem;

    private GameStats gameStats;

    public InMultiPlayerGameState(GameStateController gsc) {
        super(gsc);

        batch = new SpriteBatch();

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        this.camera.position.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0);
        this.camera.setToOrtho(false, TILE_COLUMN * TILE_SIZE, TILE_ROW * TILE_SIZE);

        this.tiledMap = new TmxMapLoader().load("level1/level1map.tmx");
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, batch);
        this.tiledMapRenderer.setView(camera);

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
    }

    @Override
    public void update(float delta) { }

    @Override
    public void render() {
        tiledMapRenderer.render();
        engine.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() { }
}
