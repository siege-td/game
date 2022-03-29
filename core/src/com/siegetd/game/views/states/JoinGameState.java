package com.siegetd.game.views.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.singletons.MyAssetManager;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.components.BackButton;
import com.siegetd.game.views.components.JoinButton;
import com.siegetd.game.views.components.MusicButton;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.TableComponent;

public class JoinGameState extends GameState {
    private final Texture background;
    private final TableComponent table;
    private final RopeComponent rope;
    private final Stage stage;

    public JoinGameState(GameStateController gsc) {
        super(gsc);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Visual components
        background = new Texture("GUI/bg.png");
        table = new TableComponent();
        rope = new RopeComponent(table);

        Table buttonTable = new Table();
        buttonTable.setFillParent(true);

        JoinButton joinButton = new JoinButton();
        joinButton.addButtonListners(gsc);
        buttonTable.add(joinButton.button);

        Skin mSkin = new Skin();
        mSkin.load(Gdx.files.internal("GUI/input_field.png"));
        TextField lobbyCode = new TextField("", mSkin);
        lobbyCode.setText("Enter code here");
        lobbyCode.setPosition(24,73);
        lobbyCode.setSize(88, 14);

        BackButton btnBack = new BackButton(table);
        btnBack.addButtonListners(gsc);
        stage.addActor(btnBack.button);
        stage.addActor(buttonTable);
        stage.addActor(lobbyCode);
        //Inputfield for x integers
        //Join lobby button
        //go back button
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

    }
}
