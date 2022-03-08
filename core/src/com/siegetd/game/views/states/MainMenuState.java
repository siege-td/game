package com.siegetd.game.views.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.views.GameState;

public class MainMenuState extends GameState{
    private Texture background;
    private Texture table;
    private Texture rope;
    private Texture joinBtn;
    private Texture hostBtn;
    private Texture settingsBtn;

    private String join;
    private String host;
    private String settings;

    private int tableWidth;
    private int tableHeight;
    private int tableX;
    private int tableY;

    private int ropeWidth;
    private int ropeLeftX;
    private int ropeY;
    private int ropeRightX;
    private int ropeRightY;

    private int btnWidth;
    private int btnHeight;
    private int btnX;
    private int joinBtnY;
    private int hostBtnY;
    private int settingsBtnY;

    //TODO: Change to FreeTypeFontParameter
    private CharSequence str;
    private final BitmapFont font;

    public MainMenuState(GameStateController gsc){
        super(gsc);
        font = new BitmapFont();
        background = new Texture("GUI/bg.png");
        table = new Texture("GUI/table.png");
        joinBtn = new Texture("GUI/button_empty_2.png");
        hostBtn = new Texture("GUI/button_empty_2.png");
        settingsBtn = new Texture("GUI/button_empty_2.png");
        rope = new Texture("GUI/rope_big.png");

        settings = "Settings";
        join = "Join Lobby";
        host = "Host Lobby";

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
        joinBtnY = ((int)(Gdx.graphics.getHeight() * 0.7) - (btnHeight / 2));
        hostBtnY = ((int)(Gdx.graphics.getHeight() * 0.5) - (btnHeight / 2));
        settingsBtnY = ((int)(Gdx.graphics.getHeight() * 0.3) - (btnHeight / 2));
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsc.setState(GameStateController.State.SETTINGS);
            dispose();
        }
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
        batch.draw(settingsBtn, btnX, settingsBtnY, btnWidth, btnHeight);
        batch.draw(joinBtn, btnX, joinBtnY, btnWidth, btnHeight);
        batch.draw(hostBtn, btnX, hostBtnY, btnWidth, btnHeight);

        font.setColor(1f, 1f, 1f, 1f);
        font.draw(batch, settings, (Gdx.graphics.getWidth() /2),  settingsBtnY);
        font.draw(batch, join, (Gdx.graphics.getWidth() /2),  joinBtnY);
        font.draw(batch, host, (Gdx.graphics.getWidth() /2),  hostBtnY);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        table.dispose();
        rope.dispose();
    }
}
