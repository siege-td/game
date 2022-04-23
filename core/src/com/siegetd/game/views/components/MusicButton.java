package com.siegetd.game.views.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.controllers.GameViewController;

public class MusicButton extends ButtonComponent {

    private ButtonComponent buttonComponent;
    private Texture buttonImg;
    private Texture buttonImgOff;
    private Vector2 pos;
    private boolean enabled;
    public Button buttonOn;
    public Button buttonOff;

    public MusicButton(WindowComponent table) {
        pos = table.getTopLeft();
        this.enabled = true;
        this.buttonComponent = new ButtonComponent();
        this.buttonImg = new Texture("GUI/button_music.png");
        this.buttonImgOff = new Texture("GUI/button_music_off.png");
        this.buttonOn = this.buttonComponent.createButton(this.buttonImg);
        this.buttonOn.setSize(table.windowWidth /10, table.windowWidth /10);
        this.buttonOn.setPosition(
                (float) (pos.x - (buttonOn.getWidth()* 0.25)),
                (float) (pos.y - (buttonOn.getHeight() * 0.75))
        );
        this.buttonOff = this.buttonComponent.createButton(this.buttonImgOff);
        this.buttonOff.setSize(table.windowWidth /10, table.windowWidth /10);
        this.buttonOff.setPosition(
                (float) (pos.x - (buttonOff.getWidth()* 0.25)),
                (float) (pos.y - (buttonOff.getHeight() * 0.75))
        );
        this.buttonOff.setVisible(false);
    }

    public void addButtonListners(final GameViewController gsc) {
        this.buttonOn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(enabled){
                    disableMusic();
                }else{
                    enableMusic();
                }
                System.out.println("enabled changed to :" + enabled);
            }
        });
        this.buttonOff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(enabled){
                    disableMusic();
                }else{
                    enableMusic();
                }
                System.out.println("enabled changed to :" + enabled);
            }
        });
    }

    private void enableMusic(){
        this.enabled = true;
        this.buttonOff.setVisible(false);
        this.buttonOn.setVisible(true);
    }

    private void disableMusic(){
        this.enabled = false;
        this.buttonOff.setVisible(true);
        this.buttonOn.setVisible(false);

    }
}
