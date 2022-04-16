package com.siegetd.game.views.components.selectentity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.siegetd.game.views.components.ButtonComponent;

public class SelectEntityModal {

    private Stage stage;
    private OrthographicCamera camera;

    private ButtonComponent buttonComponent;
    private Texture buttonImg;
    public Button button;

    public SelectEntityModal(Stage stage, OrthographicCamera camera) {
        this.stage = stage;
        this.camera = camera;
    }

    public void showModal() {
        this.buttonComponent = new ButtonComponent();
        this.buttonImg = new Texture("GUI/")
    }

    public void hideModal() {

    }
}
