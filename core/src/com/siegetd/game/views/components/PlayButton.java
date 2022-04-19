package com.siegetd.game.views.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.Globals;
import com.siegetd.game.api.SocketConnection;
import com.siegetd.game.controllers.GameStateController;

import java.net.URISyntaxException;

import io.socket.client.Socket;

public class PlayButton extends ButtonComponent {

    private ButtonComponent buttonComponent;
    private Texture buttonImg;
    private Vector2 pos;
    public Button button;

    private Socket socket;

    public PlayButton(WindowComponent table) {
        pos = table.getBottomCenter();
        this.buttonComponent = new ButtonComponent();
        this.buttonImg = new Texture("GUI/button_play.png");
        this.button = this.buttonComponent.createButton(this.buttonImg);
        this.button.setSize(table.windowWidth /5, table.windowWidth /5);
        this.button.setPosition(
                (float) (pos.x - (button.getWidth()* 0.5)),
                (float) (pos.y - (button.getHeight() * 0.5))
        );
        try {
            this.socket = SocketConnection.getInstance().getSocket();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void addButtonListnersForHostSingleplayer(final GameStateController gsc) {
        // TODO: INIT ALL LEVEL DATA HERE??
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsc.setState(GameStateController.State.IN_GAME_SINGLE);
            }
        });
    }

    public void addButtonListnersForHostMultiplayer(final GameStateController gsc) {
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                socket.emit("start_game", Globals.pin);
                gsc.setState(GameStateController.State.IN_GAME_MULTI);
            }
        });
    }
}
