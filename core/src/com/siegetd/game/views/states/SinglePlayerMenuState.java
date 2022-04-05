package com.siegetd.game.views.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.components.BackButton;
import com.siegetd.game.views.components.JoinButton;
import com.siegetd.game.views.components.PlayButton;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.TableComponent;

public class SinglePlayerMenuState extends GameState {
    private final Texture background;
    private final BackButton backButton;
    private final PlayButton playButton;
    private final TableComponent table;
    private final RopeComponent rope;
    private final Stage stage;

    public SinglePlayerMenuState(GameStateController gsc) {
        super(gsc);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        /*TODO:
           Game settings (Level layout/Difficulty?)
        */

        //GUI
        background = new Texture("GUI/bg.png");
        table = new TableComponent();
        rope = new RopeComponent(table);

        //Declare components
        backButton = new BackButton(table);
        backButton.addButtonListners(gsc);
        playButton = new PlayButton(table);
        playButton.addButtonListners(gsc);

        //Add components
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
