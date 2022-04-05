package com.siegetd.game.views.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.components.MultiPlayerButton;
import com.siegetd.game.views.components.SettingsButton;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.SinglePlayerButton;
import com.siegetd.game.views.components.TableComponent;

public class MainMenuState extends GameState{
    private Texture background;
    private TableComponent table;
    private RopeComponent rope;
    private Table buttonTable;
    private SettingsButton settingsButton;
    private SinglePlayerButton singlePlayerButton;
    private MultiPlayerButton multiPlayerButton;
    private Stage stage;

    public MainMenuState(final GameStateController gsc){
        super(gsc);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //GUI
        background = new Texture("GUI/bg.png");
        table = new TableComponent();
        rope = new RopeComponent(table);

        //Declare components
        buttonTable = new Table();
        buttonTable.setFillParent(true);

        settingsButton = new SettingsButton();
        settingsButton.addButtonListners(gsc);
        buttonTable.add(settingsButton.button).size(
                (float)(table.tableWidth / 3),
                (float) (table.tableHeight *0.3))
                .row();

        singlePlayerButton = new SinglePlayerButton();
        singlePlayerButton.addButtonListners(gsc);
        buttonTable.add(singlePlayerButton.button).size(
                (float)(table.tableWidth / 3),
                (float) (table.tableHeight *0.3))
                .row();

        multiPlayerButton = new MultiPlayerButton();
        multiPlayerButton.addButtonListners(gsc);
        buttonTable.add(multiPlayerButton.button).size(
                (float)(table.tableWidth / 3),
                (float) (table.tableHeight *0.3))
                .row();

        //Add components
        stage.addActor(buttonTable);
    }

    @Override
    public void update(float delta) {}

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
