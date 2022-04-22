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
import com.siegetd.game.EngineState;
import com.siegetd.game.api.SocketConnection;
import com.siegetd.game.controllers.GameStateController;
import com.siegetd.game.views.GameState;
import com.siegetd.game.views.components.BackButton;
import com.siegetd.game.views.components.InputButton;
import com.siegetd.game.views.components.RopeComponent;
import com.siegetd.game.views.components.WindowComponent;

import java.net.URISyntaxException;

import io.socket.emitter.Emitter;

public class JoinGameState extends GameState {
    //private JoinButton joinButton;
    private InputButton inputButton;
    private BackButton backButton;
    private Table buttonTable;
    private Texture background;
    private WindowComponent table;
    private RopeComponent rope;
    private Stage stage;
    private GlyphLayout glyphLayout;
    private float textWidth;
    private String pin;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font;

    private boolean hasJoinedLobby;

    public JoinGameState(GameStateController gsc) {
        super(gsc);

        createStage();
        createBackground();
        createButtonTable();
        createButtons();
        createFont();

        stageComponents();

        hasJoinedLobby = false;

        //socket event listeners
        try {
            SocketConnection.getInstance().getSocket().on("join_pin_invalid", invalidPin);
            SocketConnection.getInstance().getSocket().on("join_pin_valid", validPin);
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
        //try catch invalidPin
        try {
            SocketConnection.getInstance().getSocket().on("game_started", onGameStarted);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
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

    private void createButtonTable(){
        buttonTable = new Table();
        buttonTable.setFillParent(true);
    }

    private void createButtons(){
        //joinButton = new JoinButton();
        //joinButton.addButtonListnersJoinMultiplayer(gsc);
        backButton = new BackButton(table);
        backButton.addButtonListners(gsc);
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
        try {
            updateText();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void updateText() throws URISyntaxException {
        if (inputButton.listener.getText().equalsIgnoreCase("NO PIN ADDED")
                || inputButton.listener.getText().equalsIgnoreCase("INCORRECT PIN ADDED"))
        {
            pin = "PIN: " + inputButton.listener.getText();
        } else if (hasJoinedLobby){
            pin = "PIN: " + inputButton.listener.getText() + "\nWaiting for host to start..";
            if (!hasJoinedLobby) {
                SocketConnection.getInstance().getSocket().emit("join_lobby", EngineState.pin);
            }
        } else {
            inputButton.listener.incorrectPin();
        }
        glyphLayout.setText(font, pin);
        textWidth = glyphLayout.width;
    }

    private void stageComponents() {
        buttonTable.add(inputButton.button).size(
                (float)(table.windowWidth / 3),
                (float) (table.windowHeight *0.3))
                .row();
        //buttonTable.add(joinButton.button).size(
        //        (float)(table.windowWidth / 3),
        //        (float) (table.windowHeight *0.3))
        //        .row();
        stage.addActor(backButton.button);
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

    private Emitter.Listener onGameStarted = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            gsc.setState(GameStateController.State.IN_GAME_MULTI);
        }
    };

    private Emitter.Listener invalidPin = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            hasJoinedLobby = false;
            inputButton.listener.incorrectPin();
        }
    };

    private Emitter.Listener validPin = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            hasJoinedLobby = true;
        }
    };
}
