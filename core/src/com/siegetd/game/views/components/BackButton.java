package com.siegetd.game.views.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.controllers.GameViewController;

public class BackButton extends ButtonComponent {

    private ButtonComponent buttonComponent;
    private Texture buttonImg;
    private Vector2 pos;
    public Button button;

    public BackButton(WindowComponent table) {
        pos = table.getTopLeft();
        this.buttonComponent = new ButtonComponent();
        this.buttonImg = new Texture("GUI/button_close.png");
        this.button = this.buttonComponent.createButton(this.buttonImg);
        this.button.setSize(table.windowWidth /10, table.windowWidth /10);
        this.button.setPosition(
                (float) (pos.x - (button.getWidth()* 0.25)),
                (float) (pos.y - (button.getHeight() * 0.75))
        );
    }

    public void addButtonListners(final GameViewController gsc) {
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsc.setState(GameViewController.View.MENU);
            }
        });
    }
}
