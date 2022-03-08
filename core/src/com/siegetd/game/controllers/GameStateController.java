package com.siegetd.game.controllers;

import com.siegetd.game.SiegeTd;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.states.PlayState;
import com.siegetd.game.views.states.SplashState;

import java.util.Stack;

public class GameStateController {

    //Reference from main application class
    private final SiegeTd app;
    private final Stack<GameState> states;


    public enum State{
        SPLASH,
        PLAY
    }

    public GameStateController(final SiegeTd app){
        this.app = app;
        this.states = new Stack<GameState>();
        this.setState(State.SPLASH);
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
        for(GameState gs: states){
            gs.dispose();
        }
    }


    public void setState(State state){
        if(states.size() >= 1){
            states.pop().dispose();
        }
        states.push(getState(state));
    }

    private GameState getState(State state){
        switch (state){
            case SPLASH:
                return new SplashState(this);
            case PLAY:
                return new PlayState(this);
        }
        return null;
    }
}
