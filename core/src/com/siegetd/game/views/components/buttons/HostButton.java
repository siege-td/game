package com.siegetd.game.views.components.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.controllers.GameViewController;
import com.siegetd.game.views.components.buttons.GameButton;

public class HostButton extends GameButton {


    public HostButton(){
        super("GUI/create_game.png");
    }

    public void addButtonListners(final GameViewController gsc) {
        this.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsc.setState(GameViewController.View.HOST);
            }
        });
    }

}
