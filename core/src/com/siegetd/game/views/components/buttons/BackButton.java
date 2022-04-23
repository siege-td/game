package com.siegetd.game.views.components.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.controllers.GameViewController;
import com.siegetd.game.views.components.WindowComponent;
import com.siegetd.game.views.components.buttons.GameButton;

public class BackButton extends GameButton {
    private Vector2 pos;


    public BackButton(WindowComponent table) {
        super("GUI/button_close.png");
        pos = table.getTopLeft();
        getButton().setSize(table.windowWidth /10, table.windowWidth /10);
        getButton().setPosition(
                (float) (pos.x - (getButton().getWidth()* 0.25)),
                (float) (pos.y - (getButton().getHeight() * 0.75))
        );
    }

    public void addButtonListners(final GameViewController gsc) {
        this.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsc.setState(GameViewController.View.MENU);
            }
        });
    }
}
