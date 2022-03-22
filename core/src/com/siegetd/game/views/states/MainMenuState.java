package com.siegetd.game.views.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.components.ButtonHost;
import com.siegetd.game.views.components.ButtonJoin;
import com.siegetd.game.views.components.ButtonSettings;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.TableComponent;

public class MainMenuState extends GameState{
    private Texture background;
    private ButtonSettings btnSettings;
    private ButtonJoin btnJoin;
    private ButtonHost btnHost;
    private TableComponent table;
    private RopeComponent rope;
    private Table buttonTable;

    private Stage stage;

    public MainMenuState(final GameStateController gsc){
        super(gsc);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Visual components
        background = new Texture("GUI/bg.png");
        table = new TableComponent();
        rope = new RopeComponent(table);

        //functional components
        buttonTable = new Table();
        //buttonTable.setSize(table.tableWidth, table.tableHeight);
        //buttonTable.align(Align.center);
        buttonTable.setFillParent(true);

        btnSettings = new ButtonSettings();
        btnSettings.addButtonListners(gsc);
        buttonTable.add(btnSettings.button).size(btnSettings.button.getWidth() / 2,btnSettings.button.getHeight() / 2).row();

        btnJoin = new ButtonJoin();
        btnJoin.addButtonListners(gsc);
        buttonTable.add(btnJoin.button).size(btnJoin.button.getWidth() / 2,btnJoin.button.getHeight() / 2).row();

        btnHost = new ButtonHost();
        btnHost.addButtonListners(gsc);
        buttonTable.add(btnHost.button).size(btnHost.button.getWidth() / 2, btnHost.button.getHeight() / 2).row();


        stage.addActor(buttonTable);
    }

    @Override
    public void handleInput() {  }

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
        batch.draw(table.img, table.tableX,table.tableY, table.tableWidth, table.tableHeight);
        batch.draw(rope.img, rope.ropeLeftX, rope.ropeY, rope.ropeWidth, rope.img.getHeight());
        batch.draw(rope.img, rope.ropeRightX, rope.ropeY, rope.ropeWidth, rope.img.getHeight());
        batch.end();
        //Draw buttons
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        table.dispose();
        rope.dispose();
        stage.dispose();
    }
}
