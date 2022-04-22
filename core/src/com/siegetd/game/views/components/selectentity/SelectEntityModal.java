package com.siegetd.game.views.components.selectentity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.EngineState;
import com.siegetd.game.models.ecs.entities.defender.ArcherEntity;
import com.siegetd.game.models.ecs.entities.defender.MageEntity;
import com.siegetd.game.models.ecs.entities.defender.ZappEntity;
import com.siegetd.game.singletons.ScoreHandler;
import com.siegetd.game.views.components.ButtonComponent;

import java.util.concurrent.Callable;

public class SelectEntityModal {

    private Button archerButton;
    private Button mageButton;
    private Button zappButton;

    //Cost of each tower
    private static final int archerCost = 30;
    private static final int mageCost = 40;
    private static final int zappCost = 60;

    public SelectEntityModal() {
    }

    public void showModal() {
        ButtonComponent buttonComponent = new ButtonComponent();

        this.archerButton = buttonComponent.createButton(new Texture("GUI/add_archer.png"));
        this.archerButton.setSize(EngineState.camera.viewportWidth / 80, EngineState.camera.viewportWidth / 80);
        this.archerButton.setPosition(
                ((Gdx.graphics.getWidth() / 2) - (this.archerButton.getWidth() / 2)) - 80,
                ((Gdx.graphics.getHeight() / 2) - (this.archerButton.getHeight() / 2))
        );
        if (ScoreHandler.getInstance().getCurrency() < archerCost) {
            this.archerButton.setColor(Color.RED);
        }

        this.mageButton = buttonComponent.createButton(new Texture("GUI/add_mage.png"));
        this.mageButton.setSize(EngineState.camera.viewportWidth / 80, EngineState.camera.viewportWidth / 80);
        this.mageButton.setPosition(
                ((Gdx.graphics.getWidth() / 2) - (this.mageButton.getWidth() / 2)),
                ((Gdx.graphics.getHeight() / 2) - ((this.mageButton.getHeight() / 2)))
        );
        if (ScoreHandler.getInstance().getCurrency() < mageCost) {
            this.mageButton.setColor(Color.RED);
        }

        this.zappButton = buttonComponent.createButton(new Texture("GUI/add_zapp.png"));
        this.zappButton.setSize(EngineState.camera.viewportWidth / 80, EngineState.camera.viewportWidth / 80);
        this.zappButton.setPosition(
                (((Gdx.graphics.getWidth() / 2) - (this.zappButton.getWidth() / 2))) + 80,
                ((Gdx.graphics.getHeight() / 2) - ((this.zappButton.getHeight() / 2)))
        );
        if (ScoreHandler.getInstance().getCurrency() < zappCost) {
            this.zappButton.setColor(Color.RED);
        }
    }

    public void hideModal() {
        this.archerButton.setVisible(false);
        this.mageButton.setVisible(false);
        this.zappButton.setVisible(false);
    }

    public void addButtonListeners(final Vector2 entitySpawnPos, final Callable<Void> entitySpawned) {
        this.archerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ScoreHandler.getInstance().getCurrency() >= archerCost) {
                    new ArcherEntity(entitySpawnPos).create();
                    ScoreHandler.getInstance().subtractCurrency(archerCost);
                    System.out.println("Current currency: " + ScoreHandler.getInstance().getCurrency()); //REMOVE ME!!!!!!!!!!!!!!!!!!!!!
                    try {
                        entitySpawned.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                hideModal();
            }

        });

        this.mageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ScoreHandler.getInstance().getCurrency() >= mageCost) {
                    new MageEntity(entitySpawnPos).create();
                    ScoreHandler.getInstance().subtractCurrency(mageCost);
                    System.out.println("Current currency: " + ScoreHandler.getInstance().getCurrency()); //REMOVE ME!!!!!!!!!!!!!!!!!!!!!
                    try {
                        entitySpawned.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                hideModal();
            }
        });

        this.zappButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ScoreHandler.getInstance().getCurrency() >= zappCost) {
                    new ZappEntity(entitySpawnPos).create();
                    ScoreHandler.getInstance().subtractCurrency(zappCost);
                    System.out.println("Current currency: " + ScoreHandler.getInstance().getCurrency()); //REMOVE ME!!!!!!!!!!!!!!!!!!!!!
                    try {
                        entitySpawned.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                hideModal();
            }
        });
    }

    public Button getArcherButton() {
        return archerButton;
    }

    public Button getMageButton() {
        return mageButton;
    }

    public Button getZappButton() {
        return zappButton;
    }
}
