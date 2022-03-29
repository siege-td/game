package com.siegetd.game.views.states;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.models.ecs.components.MovementComponent;
import com.siegetd.game.models.ecs.components.PositionComponent;
import com.siegetd.game.models.ecs.components.VisualComponent;
import com.siegetd.game.models.ecs.entities.TestEntity;
import com.siegetd.game.models.ecs.systems.AnimationSystem;
import com.siegetd.game.models.ecs.systems.MovementSystem;
import com.siegetd.game.models.ecs.systems.RenderSystem;
import com.siegetd.game.models.ecs.systems.RenderingSystem;
import com.siegetd.game.models.map.GameMap;
import com.siegetd.game.views.GameState;

public class PlayState extends GameState{
    private GameMap gameMap;
    private RenderingSystem renderingSystem;
    private OrthographicCamera camera;
    private PooledEngine engine;

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;

    public PlayState(GameStateController gsc){
        super(gsc);


        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0);
        camera.setToOrtho(false, TILE_COLUMN * TILE_SIZE, TILE_ROW * TILE_SIZE);
        camera.update();

        tiledMap = new TmxMapLoader().load("level1/level1map.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap, batch);
        renderer.setView(camera);

        this.gameMap = new GameMap(camera);

        Texture crateTexture = new Texture(Gdx.files.internal("crate.png"));
        Texture coinTexture = new Texture(Gdx.files.internal("coin.png"));

        engine = new PooledEngine();
        engine.addSystem(new RenderSystem(camera));
        engine.addSystem(new MovementSystem());

        Entity crate = engine.createEntity();
        crate.add(new PositionComponent(50, 50));
        crate.add(new VisualComponent(new TextureRegion(crateTexture)));

        engine.addEntity(crate);

        TextureRegion coinRegion = new TextureRegion(coinTexture);

        for (int i = 0; i < 100; i++) {
            Entity coin = engine.createEntity();
            coin.add(new PositionComponent(MathUtils.random(640), MathUtils.random(480)));
            coin.add(new MovementComponent(10.0f, 100.0f));
            coin.add(new VisualComponent(coinRegion));
            engine.addEntity(coin);
        }
    }

    @Override
    public void update(float delta) {}

    @Override
    public void render() {
        renderer.render();
        engine.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
    }
}
