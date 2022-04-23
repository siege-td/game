package com.siegetd.game.views.components.buttons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.SiegeTdState;
import com.siegetd.game.api.SocketConnection;
import com.siegetd.game.controllers.GameViewController;
import com.siegetd.game.views.components.WindowComponent;

import java.net.URISyntaxException;

import io.socket.client.Socket;

public class PlayButton extends GameButton {

    private Vector2 pos;
    private Socket socket;

    public PlayButton(WindowComponent table) {
        super("GUI/button_play.png");
        pos = table.getBottomCenter();
        this.getButton().setSize(table.windowWidth /5, table.windowWidth /5);
        this.getButton().setPosition(
                (float) (pos.x - (getButton().getWidth()* 0.5)),
                (float) (pos.y - (getButton().getHeight() * 0.5))
        );
        try {
            this.socket = SocketConnection.getInstance().getSocket();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void addButtonListnersForHostSingleplayer(final GameViewController gsc) {
        this.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsc.setState(GameViewController.View.PLAY);
            }
        });
    }

    public void addButtonListnersForHostMultiplayer(final GameViewController gsc) {
        this.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                socket.emit("start_game", SiegeTdState.pin);
                gsc.setState(GameViewController.View.PLAY);
            }
        });
    }
}
