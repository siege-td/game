package com.siegetd.game.views.components.selectentity;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.models.ecs.entities.ArcherEntity;
import com.siegetd.game.models.ecs.entities.MageEntity;
import com.siegetd.game.models.ecs.entities.ZappEntity;
import com.siegetd.game.views.components.ButtonComponent;

import java.util.concurrent.Callable;

public class SelectEntityModal {

    private OrthographicCamera camera;

    private ButtonComponent buttonComponent;

    private Texture archerButtonImg;
    public Button archerButton;

    private Texture mageButtonImg;
    public Button mageButton;

    private Texture zappButtonImg;
    public Button zappButton;


    public SelectEntityModal(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void showModal() {
        this.buttonComponent = new ButtonComponent();

        this.archerButtonImg = new Texture("GUI/add_archer.png");
        this.archerButton = this.buttonComponent.createButton(archerButtonImg);
        this.archerButton.setSize(camera.viewportWidth / 80, camera.viewportWidth / 80);
        this.archerButton.setPosition(
                (((Gdx.graphics.getWidth() / 2) - (this.archerButton.getWidth() / 2))) - 80,
                ((Gdx.graphics.getHeight() / 2) - ((this.archerButton.getHeight() / 2)))
        );

        this.mageButtonImg = new Texture("GUI/add_mage.png");
        this.mageButton = this.buttonComponent.createButton(mageButtonImg);
        this.mageButton.setSize(camera.viewportWidth / 80, camera.viewportWidth / 80);
        this.mageButton.setPosition(
                ((Gdx.graphics.getWidth() / 2) - (this.mageButton.getWidth() / 2)),
                ((Gdx.graphics.getHeight() / 2) - ((this.mageButton.getHeight() / 2)))
        );

        this.zappButtonImg = new Texture("GUI/add_zapp.png");
        this.zappButton = this.buttonComponent.createButton(zappButtonImg);
        this.zappButton.setSize(camera.viewportWidth / 80, camera.viewportWidth / 80);
        this.zappButton.setPosition(
                (((Gdx.graphics.getWidth() / 2) - (this.zappButton.getWidth() / 2))) + 80,
                ((Gdx.graphics.getHeight() / 2) - ((this.zappButton.getHeight() / 2)))
        );
    }

    private void hideModal() {
        this.archerButton.setVisible(false);
        this.mageButton.setVisible(false);
        this.zappButton.setVisible(false);
    }

    public void addButtonListeners(final PooledEngine engine, final Vector2 entitySpawnPos, final Callable<Void> entitySpawned) {
        this.archerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new ArcherEntity(engine, entitySpawnPos, camera).create();
                try {
                    entitySpawned.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                hideModal();
            }
        });

        this.mageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new MageEntity(engine, entitySpawnPos, camera).create();
                try {
                    entitySpawned.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                hideModal();
            }
        });

        this.zappButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new ZappEntity(engine, entitySpawnPos, camera).create();
                try {
                    entitySpawned.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                hideModal();
            }
        });
    }

}
