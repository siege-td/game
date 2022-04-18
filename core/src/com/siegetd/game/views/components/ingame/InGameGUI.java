package com.siegetd.game.views.components.ingame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.RoundHandler;

public class InGameGUI {

    private RoundHandler roundHandler;

    public InGameGUI(OrthographicCamera camera, Stage stage) {
        RoundHandler roundHandler = new RoundHandler();

        StartRoundButton startRoundButton = new StartRoundButton(camera);
        startRoundButton.addButtonListener(roundHandler);
        stage.addActor(startRoundButton.button);
    }
}
