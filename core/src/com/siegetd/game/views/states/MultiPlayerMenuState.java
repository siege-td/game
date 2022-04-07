package com.siegetd.game.views.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.components.BackButton;
import com.siegetd.game.views.components.HostButton;
import com.siegetd.game.views.components.JoinButton;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.WindowComponent;

public class MultiPlayerMenuState extends GameState {
    private Texture background;
    private BackButton btnBack;
    private WindowComponent table;
    private RopeComponent rope;
    private JoinButton joinButton;
    private HostButton hostButton;
    private Table buttonTable;
    private Stage stage;

    public MultiPlayerMenuState(GameStateController gsc){
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
        batch.draw(table.img, table.windowX,table.windowY, table.windowWidth, table.windowHeight);
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
        table = new WindowComponent();
        rope = new RopeComponent(table);
    }

    private void createButtonTable() {
        buttonTable = new Table();
        buttonTable.setFillParent(true);
    }

    private void createButtons() {
        joinButton = new JoinButton();
        joinButton.addButtonListners(gsc);
        buttonTable.add(joinButton.button).size(
                (float)(table.windowWidth / 3),
                (float) (table.windowHeight *0.3))
                .row();
        hostButton = new HostButton();
        hostButton.addButtonListners(gsc);
        buttonTable.add(hostButton.button).size(
                (float)(table.windowWidth / 3),
                (float) (table.windowHeight *0.3))
                .row();

        btnBack = new BackButton(table);
        btnBack.addButtonListners(gsc);
    }

    private void stageComponents() {
        stage.addActor(btnBack.button);
        stage.addActor(buttonTable);
    }

    @Override
    public void dispose() {
        background.dispose();
        table.dispose();
        rope.dispose();
        stage.dispose();
    }
}
