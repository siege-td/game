package com.siegetd.game.views.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.components.BackButton;
import com.siegetd.game.views.components.InputButton;
import com.siegetd.game.views.components.JoinButton;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.TableComponent;

public class JoinGameState extends GameState {
    private final JoinButton joinButton;
    private final InputButton inputButton;
    private final BackButton backButton;
    private final Table buttonTable;
    private final Texture background;
    private final TableComponent table;
    private final RopeComponent rope;
    private final Stage stage;

    private BitmapFont font;

    public JoinGameState(GameStateController gsc) {
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
        joinButton = new JoinButton();
        joinButton.addButtonListners(gsc);
        backButton = new BackButton(table);
        backButton.addButtonListners(gsc);
        inputButton = new InputButton();
        inputButton.addButtonListners(gsc);
        font = new BitmapFont();
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DimboRegular.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = 12;
        fontParameter.color = Color.BLACK;

        font = fontGenerator.generateFont(fontParameter);

        fontGenerator.dispose();

        //Stage components
        buttonTable.add(inputButton.button).size(
                (float)(table.tableWidth / 3),
                (float) (table.tableHeight *0.3))
                .row();
        buttonTable.add(joinButton.button).size(
                (float)(table.tableWidth / 3),
                (float) (table.tableHeight *0.3))
                .row();
        stage.addActor(backButton.button);
        stage.addActor(buttonTable);
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
        font.draw(batch,
                "PIN: " + inputButton.listener.getText(),
                (float)((Gdx.graphics.getWidth() /2) - (font.getRegion().getRegionWidth() / 2)),
                table.getBottomCenter().y + (float)(table.tableHeight * 0.9));
        stage.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        table.dispose();
        rope.dispose();
        stage.dispose();
        font.dispose();
    }
}
