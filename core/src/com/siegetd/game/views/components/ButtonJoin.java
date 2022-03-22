package com.siegetd.game.views.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.controllers.GameStateController;

public class ButtonJoin extends ButtonComponent {

    private ButtonComponent buttonComponent;
    private Texture buttonImg;
    public Button button;

    public ButtonJoin() {
        this.buttonComponent = new ButtonComponent();
        this.buttonImg = new Texture("GUI/join_game.png");
        this.button = this.buttonComponent.createButton(0.7f, this.buttonImg);
    }

    public void addButtonListners(final GameStateController gsc) {
        System.out.println("gsc: " + gsc);
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsc.setState(GameStateController.State.LOBBY);
            }
        });
    }
}

