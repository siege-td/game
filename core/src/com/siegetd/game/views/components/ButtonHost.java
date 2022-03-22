package com.siegetd.game.views.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.controllers.GameStateController;

public class ButtonHost {

    private ButtonComponent buttonComponent;
    private Button button;
    private Texture buttonImg;

    public ButtonHost(){
//        img = new Texture("GUI/create_game.png");
//        btn = new Button(new TextureRegionDrawable(new TextureRegion(img)));
//        btnWidth = (int)(btn.getWidth()/2);
//        btnHeight = (int)(btn.getHeight()/2);
//        btnX = ((Gdx.graphics.getWidth() / 2) - (btnWidth / 2));
//        btnY = ((int)(Gdx.graphics.getHeight() * 0.5) - (btnHeight / 2));
//        btn.setPosition(btnX, btnY);
//        btn.setSize(btnWidth, btnHeight);

        this.buttonComponent = new ButtonComponent();
        this.buttonImg = new Texture("GUI/create_game.png");
        this.button = this.buttonComponent.createButton(0.5f, this.buttonImg);
    }

    public void addButtonListners(final GameStateController gsc) {
        System.out.println("gsc: " + gsc);
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsc.setState(GameStateController.State.LOBBY);
                buttonComponent.dispose(buttonImg);
            }
        });
    }

}
