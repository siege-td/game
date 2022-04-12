package com.siegetd.game.views.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.Globals;
import com.siegetd.game.api.SocketConnection;
import com.siegetd.game.controllers.GameStateController;

import java.net.URISyntaxException;

import io.socket.client.Socket;

public class JoinButton extends ButtonComponent {

    private ButtonComponent buttonComponent;
    private Texture buttonImg;
    public Button button;

    private Socket socket;

    public JoinButton() {
        this.buttonComponent = new ButtonComponent();
        this.buttonImg = new Texture("GUI/join_game.png");
        this.button = this.buttonComponent.createButton( this.buttonImg);
        try {
            this.socket = SocketConnection.getInstance().getSocket();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void addButtonListners(final GameStateController gsc) {
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsc.setState(GameStateController.State.JOIN);
            }
        });
    }

    public void addButtonListnersJoinMultiplayer(final GameStateController gsc) {
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                socket.emit("join_lobby", Globals.pin);
                gsc.setState(GameStateController.State.IN_GAME_MULTI);
            }
        });
    }
}

