package com.siegetd.game.views.components.ingame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InGameGUI {

    public InGameGUI(OrthographicCamera camera, Stage stage) {
        StartRoundButton startRoundButton = new StartRoundButton(camera);
        startRoundButton.addButtonListener();
        stage.addActor(startRoundButton.button);
    }
}
