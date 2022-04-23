package com.siegetd.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.controllers.GameViewController;
import com.siegetd.game.views.components.BackButton;
import com.siegetd.game.views.components.PlayButton;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.WindowComponent;

public class SinglePlayerMenuView extends GameView {
    private Texture background;
    private BackButton backButton;
    private PlayButton playButton;
    private WindowComponent window;
    private RopeComponent rope;
    private Stage stage;

    public SinglePlayerMenuView(GameViewController gsc) {
        super(gsc);
        /*TODO:
           Game settings (Level layout/Difficulty?)
        */

        createStage();
        createBackground();
        createButtons();

        stageComponents();
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

    private void createButtons() {
        backButton = new BackButton(window);
        backButton.addButtonListners(gsc);
        playButton = new PlayButton(window);
        playButton.addButtonListnersForHostSingleplayer(gsc);
    }

    private void stageComponents() {
        stage.addActor(backButton.button);
        stage.addActor(playButton.button);
    }

    @Override
    public void update(float delta) {

    }

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

    @Override
    public void dispose() {
        background.dispose();
        window.dispose();
        rope.dispose();
        stage.dispose();
    }
}
