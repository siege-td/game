package com.siegetd.game.views.components.ingame;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InGameGUI {

    public InGameGUI(OrthographicCamera camera, Stage stage, PooledEngine engine) {
        StartRoundButton startRoundButton = new StartRoundButton(camera);
        startRoundButton.addButtonListener(engine);
        stage.addActor(startRoundButton.button);
    }
}
