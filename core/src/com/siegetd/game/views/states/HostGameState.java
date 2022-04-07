package com.siegetd.game.views.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.components.BackButton;
import com.siegetd.game.views.components.PlayButton;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.TableComponent;

public class HostGameState extends GameState {
    private Texture background;
    private BackButton backButton;
    private PlayButton playButton;
    private TableComponent table;
    private RopeComponent rope;
    private Stage stage;

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font;
    private GlyphLayout glyphLayout;
    private float textWidth;
    private String pin;

    public HostGameState(GameStateController gsc) {
        super(gsc);
        /*TODO:
           View players in lobby(?)
           Display Game-Pin
           Game settings (Level layout/Difficulty?)
        */
        createStage();
        createBackground();
        createButtons();
        createFont();
        setText();

        stageComponents();
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
                glyphLayout,
                (Gdx.graphics.getWidth() - textWidth)/2,
                table.getBottomCenter().y + (float)(table.tableHeight * 0.9));
        batch.end();
        stage.draw();
    }

    private void createStage() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

    }

    private void createBackground() {
        background = new Texture("GUI/bg.png");
        table = new TableComponent();
        rope = new RopeComponent(table);
    }

    private void createButtons(){
        backButton = new BackButton(table);
        backButton.addButtonListners(gsc);
        playButton = new PlayButton(table);
        playButton.addButtonListners(gsc);
    }

    private void createFont(){
        font = new BitmapFont();
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DimboRegular.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = 30;
        fontParameter.color = Color.WHITE;

        font = fontGenerator.generateFont(fontParameter);

        glyphLayout = new GlyphLayout();
    }

    private void setText(){
        pin = "LOBBY-PIN:\n12345"; // + getLobbyPinApiCall
        glyphLayout.setText(font, pin);
        textWidth = glyphLayout.width;
    }

    private void stageComponents(){
        stage.addActor(backButton.button);
        stage.addActor(playButton.button);
    }


    @Override
    public void dispose() {
        background.dispose();
        table.dispose();
        rope.dispose();
        stage.dispose();
        font.dispose();
        fontGenerator.dispose();
    }
}
