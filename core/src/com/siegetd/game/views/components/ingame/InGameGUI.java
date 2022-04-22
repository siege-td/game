package com.siegetd.game.views.components.ingame;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.EngineState;

public class InGameGUI {

    public InGameGUI() {
        StartRoundButton startRoundButton = new StartRoundButton();
        startRoundButton.addButtonListener();
        EngineState.stage.addActor(startRoundButton.button);
    }
}
