package com.siegetd.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.models.map.GameMap;

public class EngineState {
    public static String pin = "";
    public static GameMap gameMap = null;
    public static PooledEngine ecsEngine = null;
    public static Stage stage = null;
    public static OrthographicCamera camera = null;
    public static SpriteBatch batch = null;
}
