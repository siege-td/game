package com.siegetd.game.models.ecs.components;

public class StateComponent {
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
