package com.siegetd.game.views.components.selectentity;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.siegetd.game.models.ECS.entities.defender.ArcherEntity;
import com.siegetd.game.models.ECS.entities.defender.MageEntity;
import com.siegetd.game.models.ECS.entities.defender.ZappEntity;
import com.siegetd.game.views.components.ButtonComponent;

import java.util.concurrent.Callable;

public class SelectEntityModal {

    private final OrthographicCamera camera;

    private Button archerButton;

    private Button mageButton;

    private Button zappButton;



    public SelectEntityModal(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void showModal() {
        ButtonComponent buttonComponent = new ButtonComponent();

        this.archerButton = buttonComponent.createButton(new Texture("GUI/add_archer.png"));
        this.archerButton.setSize(camera.viewportWidth / 80, camera.viewportWidth / 80);
        this.archerButton.setPosition(
                ((Gdx.graphics.getWidth() / 2) - (this.archerButton.getWidth() / 2)) - 80,
                ((Gdx.graphics.getHeight() / 2) - (this.archerButton.getHeight() / 2))
        );

        this.mageButton = buttonComponent.createButton(new Texture("GUI/add_mage.png"));
        this.mageButton.setSize(camera.viewportWidth / 80, camera.viewportWidth / 80);
        this.mageButton.setPosition(
                ((Gdx.graphics.getWidth() / 2) - (this.mageButton.getWidth() / 2)),
                ((Gdx.graphics.getHeight() / 2) - ((this.mageButton.getHeight() / 2)))
        );

        this.zappButton = buttonComponent.createButton(new Texture("GUI/add_zapp.png"));
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
