package com.siegetd.game.models.map;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameMapClickListener extends ClickListener {

    private GameMapActor actor;

    public GameMapClickListener(GameMapActor actor) {
        this.actor = actor;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        System.out.println(actor.getCell() + "clicked");
    }
}
