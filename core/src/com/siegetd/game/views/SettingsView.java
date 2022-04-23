package com.siegetd.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.siegetd.game.controllers.GameViewController;
import com.siegetd.game.views.components.BackButton;
import com.siegetd.game.views.components.MusicButton;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.WindowComponent;

public class SettingsView extends GameView {
    private Texture background;
    private WindowComponent table;
    private Table buttonTable;
    private MusicButton musicButton;
    private BackButton backButton;
    private RopeComponent rope;
    private Stage stage;

    public SettingsView(GameViewController gsc){
        super(gsc);
        /*
        TODO: Edit music & Sound
         */
        createStage();
        createBackground();
        createButtonTable();
        createButtons();

        stageComponents();
    }

    private void createStage() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    }

    private void createBackground() {
        background = new Texture("GUI/bg.png");
        table = new WindowComponent();
        rope = new RopeComponent(table);
    }

    private void createButtonTable() {
        buttonTable = new Table();
        buttonTable.setFillParent(true);
    }

    private void createButtons() {
        musicButton = new MusicButton(table);
        musicButton.addButtonListners(gsc);
        buttonTable.add(musicButton.buttonOn);
        buttonTable.add(musicButton.buttonOff);

        backButton = new BackButton(table);
        backButton.addButtonListners(gsc);
    }

    private void stageComponents() {
        stage.addActor(backButton.button);
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
        batch.draw(table.img, table.windowX,table.windowY, table.windowWidth, table.windowHeight);
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
