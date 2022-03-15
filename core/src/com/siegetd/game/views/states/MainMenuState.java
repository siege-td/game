package com.siegetd.game.views.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.views.GameState;

public class MainMenuState extends GameState{
    private Texture background, settingsBtn, hostBtn, joinImg,rope,table;
    private Button btnSettings, host, join;

    private int tableWidth;
    private int tableHeight;
    private int tableX;
    private int tableY;

    private int ropeWidth;
    private int ropeLeftX;
    private int ropeY;
    private int ropeRightX;

    private int btnWidth;
    private int btnHeight;
    private int btnX;
    private int joinBtnY;
    private int hostBtnY;
    private int settingsBtnY;

    Stage stage;

    public MainMenuState(final GameStateController gsc){
        super(gsc);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        background = new Texture("GUI/bg.png");
        table = new Texture("GUI/table.png");
        //joinImg = new Texture("GUI/join_game.png");
        //hostBtn = new Texture("GUI/create_game.png");
        settingsBtn = new Texture("GUI/settings.png");
        rope = new Texture("GUI/rope_big.png");

        //Centers table/window  and scales it to 0.75-0.60
        tableHeight = (int)(Gdx.graphics.getHeight() * 0.75);
        tableWidth = (int)(Gdx.graphics.getWidth() * 0.6);
        tableX = (Gdx.graphics.getWidth() / 2) - (tableWidth / 2);
        tableY = (Gdx.graphics.getHeight()/ 2) - (tableHeight / 2);

        //Scales the ropes to 0.75, attaches them to table/window based on graphics and tablewidth
        ropeWidth = (int) (rope.getWidth()*0.75);
        ropeY =  (Gdx.graphics.getHeight()/ 2) + (tableHeight/3);
        ropeLeftX =  (Gdx.graphics.getWidth() / 2) - (tableWidth / 3);
        ropeRightX =  (Gdx.graphics.getWidth() / 2) + (tableWidth / 3)- rope.getWidth();

        //TODO: Base the Y-coordinates and size(?) to table instead of graphics
        btnWidth = (int)(settingsBtn.getWidth()/2);
        btnHeight = (int)(settingsBtn.getHeight()/2);
        btnX = ((Gdx.graphics.getWidth() / 2) - (btnWidth / 2));
        //joinBtnY = ((int)(Gdx.graphics.getHeight() * 0.7) - (btnHeight / 2));
        //hostBtnY = ((int)(Gdx.graphics.getHeight() * 0.5) - (btnHeight / 2));
        settingsBtnY = ((int)(Gdx.graphics.getHeight() * 0.3) - (btnHeight / 2));

        btnSettings = new Button(new TextureRegionDrawable(new TextureRegion(settingsBtn)));
        btnSettings.setPosition(btnX, settingsBtnY);
        btnSettings.setSize(settingsBtn.getWidth()/2, settingsBtn.getHeight()/2);
        stage.addActor(btnSettings);
        Gdx.input.setInputProcessor(stage);
        btnSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsc.setState(GameStateController.State.SETTINGS);
                dispose();
            }
        } );

    }

    @Override
    public void handleInput() {

  }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1f);

        batch.begin();
        batch.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(table, tableX,tableY, tableWidth, tableHeight );
        batch.draw(rope, ropeLeftX, ropeY, ropeWidth, rope.getHeight());
        batch.draw(rope, ropeRightX, ropeY, ropeWidth, rope.getHeight());
        //batch.draw(settingsBtn, btnX, settingsBtnY, btnWidth, btnHeight);
        stage.draw();
        //batch.draw(joinBtn, btnX, joinBtnY, btnWidth, btnHeight);
        //batch.draw(hostBtn, btnX, hostBtnY, btnWidth, btnHeight);

        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        table.dispose();
        rope.dispose();
        settingsBtn.dispose();
        //joinBtn.dispose();
        //hostBtn.dispose();
    }
}
