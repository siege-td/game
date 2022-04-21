package com.siegetd.game.controllers;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.siegetd.game.EngineState;
import com.siegetd.game.models.ecs.components.TransformComponent;

import java.awt.Point;
import java.awt.Rectangle;

public class DefendAttackPair {
    Entity attacker;
    Entity defender;
    private SpriteBatch batch;

    Pixmap origProjectileImg = new Pixmap(Gdx.files.internal("projectile/stone_small.png"));
    Pixmap scaledProjectileImg = new Pixmap(
            ((TILE_SIZE * TILE_COLUMN) / TILE_COLUMN),
            ((TILE_SIZE * TILE_ROW) / TILE_ROW),
            origProjectileImg.getFormat()
    );

    private Texture ammo;

    private Vector2 bulletPosition;
    private Vector2 bulletDirection;

    private Boolean intersects;
    private Vector2 attackerPosition;
    private Vector2 defenderPosition;

    private int TOWER_RADIUS = TILE_SIZE * 5;


    public DefendAttackPair(SpriteBatch batch, Entity defender, Entity attacker){
        this.attacker = attacker;
        this.defender = defender;
        this.batch = batch;

        this.attackerPosition = attacker.getComponent(TransformComponent.class).position;
        this.defenderPosition = defender.getComponent(TransformComponent.class).position;

        this.bulletPosition = new Vector2(defender.getComponent(TransformComponent.class).position.x, defender.getComponent(TransformComponent.class).position.y);
        checkIfIntersects();

        scaledProjectileImg.drawPixmap(scaledProjectileImg,
                0, 0, origProjectileImg.getWidth(), origProjectileImg.getHeight(),
                0, 0, scaledProjectileImg.getWidth(), scaledProjectileImg.getHeight()
        );

        //ammo = new Texture(scaledProjectileImg);
        ammo = new Texture("projectile/stone_small.png");
    }

    private void checkIfIntersects(){
        java.awt.Rectangle towerRect = new Rectangle((int) defenderPosition.x - (TOWER_RADIUS / 2), (int) defenderPosition.y - (TOWER_RADIUS / 2), TOWER_RADIUS , TOWER_RADIUS);
        java.awt.Rectangle attackerRect = new Rectangle((int) attackerPosition.x - (TILE_SIZE / 2), (int) attackerPosition.y - (TILE_SIZE / 2), TILE_SIZE , TILE_SIZE);

        this.intersects = towerRect.intersects(attackerRect);
    }

    private void calculateDirection(){
        this.bulletDirection = new Vector2(attackerPosition.x - defenderPosition.x, attackerPosition.y - defenderPosition.y).nor();
    }

    private void checkIfReachedTarget(){
        java.awt.Rectangle attackerHitBox = new Rectangle((int) attackerPosition.x - (TILE_SIZE / 2), (int) attackerPosition.y - (TILE_SIZE / 2), TILE_SIZE / 2 , TILE_SIZE / 2);

        if(attackerHitBox.contains(new Point((int) bulletPosition.x,(int) bulletPosition.y))){
            this.bulletPosition.x = defenderPosition.x;
            this.bulletPosition.y = defenderPosition.y;
            //TODO: Explosion animation
        }
    }

    public void draw(){
        checkIfIntersects();

        if(intersects){

            calculateDirection();
            checkIfReachedTarget();

            EngineState.batch.begin();
            EngineState.batch.draw(ammo, bulletPosition.x, bulletPosition.y, ammo.getWidth(), ammo.getHeight());
            EngineState.batch.end();

            bulletPosition.x += bulletDirection.x * 200 * Gdx.graphics.getDeltaTime();
            bulletPosition.y += bulletDirection.y * 200 * Gdx.graphics.getDeltaTime();
        }

    }
}
