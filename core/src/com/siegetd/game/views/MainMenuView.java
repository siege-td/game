package com.siegetd.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.siegetd.game.controllers.GameViewController;
import com.siegetd.game.views.components.MultiPlayerButton;
import com.siegetd.game.views.components.SettingsButton;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.SinglePlayerButton;
import com.siegetd.game.views.components.WindowComponent;

public class MainMenuView extends GameView {
    private Texture background;
    private WindowComponent window;
    private RopeComponent rope;
    private Table buttonTable;
    private SettingsButton settingsButton;
    private SinglePlayerButton singlePlayerButton;
    private MultiPlayerButton multiPlayerButton;
    private Stage stage;

    public MainMenuView(final GameViewController gsc){
        super(gsc);

        createStage();
        createBackground();
        createButtonTable();
        createButtons();

        stageComponents();
    }

    @Override
    public void update(float delta) {}

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1f);

        batch.begin();
        batch.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(window.img, window.windowX, window.windowY, window.windowWidth, window.windowHeight);
        batch.draw(rope.img, rope.ropeLeftX, rope.ropeY, rope.ropeWidth, rope.img.getHeight());
        batch.draw(rope.img, rope.ropeRightX, rope.ropeY, rope.ropeWidth, rope.img.getHeight());
        batch.end();

        stage.draw();
    }

    private void createStage() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    }

    private void createBackground() {
        background = new Texture("GUI/bg.png");
        window = new WindowComponent();
        rope = new RopeComponent(window);
    }

    private void createButtonTable() {
        buttonTable = new Table();
        buttonTable.setFillParent(true);

    }

    private void createButtons() {
        settingsButton = new SettingsButton();
        settingsButton.addButtonListners(gsc);
        buttonTable.add(settingsButton.button).size(
                (float)(window.windowWidth / 3),
                (float) (window.windowHeight *0.3))
                .row();

        singlePlayerButton = new SinglePlayerButton();
        singlePlayerButton.addButtonListners(gsc);
        buttonTable.add(singlePlayerButton.button).size(
                (float)(window.windowWidth / 3),
                (float) (window.windowHeight *0.3))
                .row();

        multiPlayerButton = new MultiPlayerButton();
        multiPlayerButton.addButtonListners(gsc);
        buttonTable.add(multiPlayerButton.button).size(
                (float)(window.windowWidth / 3),
                (float) (window.windowHeight *0.3))
                .row();
    }


    private void stageComponents() {
        stage.addActor(buttonTable);
    }

    @Override
    public void dispose() {
        background.dispose();
        window.dispose();
        rope.dispose();
        stage.dispose();
    }
}
