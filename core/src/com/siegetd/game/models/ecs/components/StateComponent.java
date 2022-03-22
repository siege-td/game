package com.siegetd.game.models.ecs.components;

import com.badlogic.ashley.core.Component;

public class StateComponent implements Component {
    private State state = State.NORMAL;
    public float time = 0f;
    public boolean isLooping = false;

    public void setState(State newState) {
        state = newState;
        time = 0f;
    }

    public State getState() {
        return state;
    }
}
