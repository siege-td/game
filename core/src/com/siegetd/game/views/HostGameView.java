package com.siegetd.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.siegetd.game.EngineState;
import com.siegetd.game.api.SocketConnection;
import com.siegetd.game.controllers.GameViewController;
import com.siegetd.game.views.components.BackButton;
import com.siegetd.game.views.components.InputButton;
import com.siegetd.game.views.components.PlayButton;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.WindowComponent;

import java.net.URISyntaxException;

import io.socket.emitter.Emitter;

public class HostGameView extends GameView {
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

    private boolean hasHostedLobby;

    public HostGameView(GameViewController gsc) {
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

        hasHostedLobby = false;

        //socket event listeners
        try {
            SocketConnection.getInstance().getSocket().on("create_pin_valid", pinValid);
            SocketConnection.getInstance().getSocket().on("create_pin_exists", pinAlreadyExists);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
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

        try {
            updateText();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        font.draw(batch,
                glyphLayout,
                (Gdx.graphics.getWidth() - textWidth)/2,
                table.getBottomCenter().y + (float)(table.windowHeight * 0.85));
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

    private void updateText() throws URISyntaxException {
        if (inputButton.listener.getText().equalsIgnoreCase("NO PIN ADDED")
        || inputButton.listener.getText().equalsIgnoreCase("PIN ALREADY EXISTS")) {
            pin = "LOBBY-PIN:\n" + inputButton.listener.getText(); // + getLobbyPinApiCall
        } else {
            pin = "LOBBY-PIN:\n" + inputButton.listener.getText();
            if (!hasHostedLobby) {
                SocketConnection.getInstance().getSocket().emit("new_lobby", EngineState.pin);
            }
        }
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

    private Emitter.Listener pinAlreadyExists = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            inputButton.listener.pinAlreadyExists();
            hasHostedLobby = false;
        }
    };

    private Emitter.Listener pinValid = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            hasHostedLobby = true;
            inputButton.button.setVisible(false);
        }
    };

}
