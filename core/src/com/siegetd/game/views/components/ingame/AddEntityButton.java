package com.siegetd.game.views.components.ingame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.views.components.ButtonComponent;

public class AddEntityButton extends ButtonComponent {

    private ButtonComponent buttonComponent;
    private Texture buttonImg;
    public Button button;

    public AddEntityButton(OrthographicCamera camera) {
        this.buttonComponent = new ButtonComponent();
        this.buttonImg = new Texture("GUI/button_play.png");
        this.button = this.buttonComponent.createButton(this.buttonImg);
        this.button.setSize(camera.viewportWidth / 80, camera.viewportWidth / 80);
        this.button.setPosition(10, 10);
    }

    public void addButtonListeners() {
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("OIHGOIH");
            }
        });
    }
}
