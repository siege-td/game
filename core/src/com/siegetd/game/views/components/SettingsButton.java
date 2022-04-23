package com.siegetd.game.views.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.controllers.GameViewController;

public class SettingsButton extends ButtonComponent {

    private ButtonComponent buttonComponent;
    private Texture buttonImg;
    public Button button;

    public SettingsButton() {
        this.buttonComponent = new ButtonComponent();
        this.buttonImg = new Texture("GUI/settings.png");
        this.button = this.buttonComponent.createButton( this.buttonImg);
    }

    public void addButtonListners(final GameViewController gsc) {
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsc.setState(GameViewController.View.SETTINGS);
            }
        });
    }
}
