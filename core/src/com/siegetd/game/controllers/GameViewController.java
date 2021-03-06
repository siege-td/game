package com.siegetd.game.controllers;

import com.siegetd.game.SiegeTd;
import com.siegetd.game.views.GameView;
import com.siegetd.game.views.HostGameView;
import com.siegetd.game.views.JoinGameView;
import com.siegetd.game.views.LobbyView;
import com.siegetd.game.views.MainMenuView;
import com.siegetd.game.views.MultiPlayerMenuView;
import com.siegetd.game.views.PlayView;
import com.siegetd.game.views.SinglePlayerMenuView;

import java.util.Stack;

public class GameViewController {

    //Reference from main application class
    private final SiegeTd app;
    private final Stack<GameView> states;

    public enum View {
        MENU,
        LOBBY,
        MULTI,
        SINGLE,
        JOIN,
        HOST,
        PLAY
    }

    public GameViewController(final SiegeTd app){
        this.app = app;
        this.states = new Stack<GameView>();
        this.setState(View.MENU);
    }

    public SiegeTd application(){
        return app;
    }

    public void update(float delta){
        states.peek().update(delta);
    }

    public void render(){
        states.peek().render();
    }

    public void dispose(){
        for(GameView gs: states){
            gs.dispose();
        }
    }


    public void setState(View view){
        if(states.size() >= 1){
            states.pop().dispose();
        }
        states.push(getState(view));
    }

    private GameView getState(View view){
        switch (view){
            case MENU:
                return new MainMenuView(this);
            case LOBBY:
                return new LobbyView(this);
            case MULTI:
                return new MultiPlayerMenuView(this);
            case SINGLE:
                return new SinglePlayerMenuView(this);
            case JOIN:
                return new JoinGameView(this);
            case HOST:
                return new HostGameView(this);
            case PLAY:
                return new PlayView(this);
        }
        return null;
    }
}
