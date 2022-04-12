package com.siegetd.game.views.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.components.BackButton;
import com.siegetd.game.views.components.InputButton;
import com.siegetd.game.views.components.PlayButton;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.WindowComponent;

public class HostGameState extends GameState {
    private Texture background;
    private BackButton backButton;
    private InputButton inputButton;
    private PlayButton playButton;
    private WindowComponent table;
    private RopeComponent rope;
    private Stage stage;
    private Table buttonTable;

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
        createButtonTable();
        createButtons();
        createFont();

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
        batch.draw(table.img, table.windowX,table.windowY, table.windowWidth, table.windowHeight);
        batch.draw(rope.img, rope.ropeLeftX, rope.ropeY, rope.ropeWidth, rope.img.getHeight());
        batch.draw(rope.img, rope.ropeRightX, rope.ropeY, rope.ropeWidth, rope.img.getHeight());
        updateText();
        font.draw(batch,
                glyphLayout,
                (Gdx.graphics.getWidth() - textWidth)/2,
                table.getBottomCenter().y + (float)(table.windowHeight * 0.9));
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

    private void createButtons(){
        backButton = new BackButton(table);
        backButton.addButtonListners(gsc);
        playButton = new PlayButton(table);
        playButton.addButtonListnersForHostMultiplayer(gsc);
        inputButton = new InputButton();
        inputButton.addButtonListners(gsc);
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

    private void updateText(){
        pin = "LOBBY-PIN:\n" + inputButton.listener.getText(); // + getLobbyPinApiCall
        glyphLayout.setText(font, pin);
        textWidth = glyphLayout.width;
    }

    private void stageComponents(){
        buttonTable.add(inputButton.button).size(
                (float)(table.windowWidth / 3),
                (float) (table.windowHeight *0.3))
                .row();
        stage.addActor(backButton.button);
        stage.addActor(playButton.button);
        stage.addActor(buttonTable);
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
