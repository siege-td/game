package com.siegetd.game.views.components.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.api.SocketConnection;
import com.siegetd.game.controllers.GameViewController;
import com.siegetd.game.views.components.buttons.GameButton;

import java.net.URISyntaxException;

public class SinglePlayerButton extends GameButton {

    public SinglePlayerButton() {
        super("GUI/single_player.png");
    }

    public void addButtonListners(final GameViewController gsc) {
        this.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    SocketConnection.getInstance().getSocket().emit("start_init_round");
                } catch (URISyntaxException e){e.printStackTrace();}
                gsc.setState(GameViewController.View.PLAY);
            }
        });
    }
}

