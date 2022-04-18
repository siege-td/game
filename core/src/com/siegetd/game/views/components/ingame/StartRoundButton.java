package com.siegetd.game.views.components.ingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.RoundHandler;
import com.siegetd.game.views.components.ButtonComponent;

public class StartRoundButton extends ButtonComponent {

    private ButtonComponent buttonComponent;
    public Button button;

    public StartRoundButton(OrthographicCamera camera) {
        this.buttonComponent = new ButtonComponent();
        this.button = this.buttonComponent.createButton(new Texture("GUI/button_play.png"));
        this.button.setSize(camera.viewportWidth / 80, camera.viewportWidth / 80);
        this.button.setPosition(Gdx.graphics.getWidth() - (this.button.getWidth() + 10), 10);
    }

    public void addButtonListener(RoundHandler roundHandler) {
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("SAVAAA");
            }
        });
    }
}
