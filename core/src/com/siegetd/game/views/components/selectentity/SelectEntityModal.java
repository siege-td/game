package com.siegetd.game.views.components.selectentity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.siegetd.game.SiegeTdState;
import com.siegetd.game.models.ecs.entities.defender.ArcherEntity;
import com.siegetd.game.models.ecs.entities.defender.MageEntity;
import com.siegetd.game.models.ecs.entities.defender.ZappEntity;
import com.siegetd.game.controllers.ScoreController;

public class SelectEntityModal {

    private Button archerButton;
    private Button mageButton;
    private Button zappButton;
    private Table buttonTable;

    //Cost of each tower
    private static final int archerCost = 30;
    private static final int mageCost = 40;
    private static final int zappCost = 60;

    public SelectEntityModal() {
    }

    public void showModal() {
        this.buttonTable = new Table();
        this.buttonTable.setFillParent(true);
        this.buttonTable.bottom().right();

        this.archerButton = new Button(new TextureRegionDrawable(new Texture("GUI/add_archer.png")));
        this.buttonTable.add(archerButton).size(SiegeTdState.camera.viewportWidth / 80, SiegeTdState.camera.viewportWidth / 80);
        if (ScoreController.getInstance().getCurrency() < archerCost) {
            this.archerButton.setColor(Color.RED);
        }

        this.mageButton = new Button(new TextureRegionDrawable(new Texture("GUI/add_mage.png")));
        this.buttonTable.add(mageButton).size(SiegeTdState.camera.viewportWidth / 80, SiegeTdState.camera.viewportWidth / 80);
        if (ScoreController.getInstance().getCurrency() < mageCost) {
            this.mageButton.setColor(Color.RED);
        }

        this.zappButton = new Button(new TextureRegionDrawable(new Texture("GUI/add_zapp.png")));
        this.buttonTable.add(zappButton).size(SiegeTdState.camera.viewportWidth / 80, SiegeTdState.camera.viewportWidth / 80);
        if (ScoreController.getInstance().getCurrency() < zappCost) {
            this.zappButton.setColor(Color.RED);
        }


    }

    public void hideModal() {
        this.archerButton.setVisible(false);
        this.mageButton.setVisible(false);
        this.zappButton.setVisible(false);
    }

    public void addButtonListeners(final Vector2 entitySpawnPos) {
        this.archerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ScoreController.getInstance().getCurrency() >= archerCost) {
                    new ArcherEntity(entitySpawnPos).create();
                    ScoreController.getInstance().subtractCurrency(archerCost);
                }
                hideModal();
            }

        });

        this.mageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ScoreController.getInstance().getCurrency() >= mageCost) {
                    new MageEntity(entitySpawnPos).create();
                    ScoreController.getInstance().subtractCurrency(mageCost);
                }
                hideModal();
            }
        });

        this.zappButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ScoreController.getInstance().getCurrency() >= zappCost) {
                    new ZappEntity(entitySpawnPos).create();
                    ScoreController.getInstance().subtractCurrency(zappCost);
                }
                hideModal();
            }
        });
    }

    public Table getButtonTable() {
        return buttonTable;
    }

}
