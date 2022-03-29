package com.siegetd.game.views.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.components.BackButton;
import com.siegetd.game.views.components.MusicButton;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.SettingsButton;
import com.siegetd.game.views.components.TableComponent;

public class SettingsState extends GameState{
    private final Texture background;
    private final TableComponent table;
    private final RopeComponent rope;
    private final Stage stage;

    public SettingsState(GameStateController gsc){
        super(gsc);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Visual components
        background = new Texture("GUI/bg.png");
        table = new TableComponent();
        rope = new RopeComponent(table);

        Table buttonTable = new Table();
        buttonTable.setFillParent(true);
        MusicButton musicButton = new MusicButton(table);
        musicButton.addButtonListners(gsc);
        buttonTable.add(musicButton.button);

        //pos = new Vector2(table.tableX, table.tableY + table.tableHeight);
        BackButton btnBack = new BackButton(table);
        btnBack.addButtonListners(gsc);
        stage.addActor(btnBack.button);
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
