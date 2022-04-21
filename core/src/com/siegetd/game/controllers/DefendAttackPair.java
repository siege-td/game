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
import com.siegetd.game.models.ECS.components.TransformComponent;

import java.awt.Point;
import java.awt.Rectangle;

public class DefendAttackPair {
    Entity attacker;
    Entity defender;
    private SpriteBatch batch;
    private Texture ammo = new Texture(Gdx.files.internal("ammo/stone_small.png"));

    private Vector2 position;
    private Vector2 speed;
    private Vector2 direction;


    public DefendAttackPair(SpriteBatch batch, Entity defender, Entity attacker){
        this.attacker = attacker;
        this.defender = defender;
        this.batch = batch;

        this.position = new Vector2(defender.getComponent(TransformComponent.class).position.x, defender.getComponent(TransformComponent.class).position.y);
        this.speed = new Vector2(attacker.getComponent(TransformComponent.class).position.x, attacker.getComponent(TransformComponent.class).position.y);

    }

    private void calculateDirection(){
        Vector2 attackerPosition = attacker.getComponent(TransformComponent.class).position;
        Vector2 defenderPosition = defender.getComponent(TransformComponent.class).position;

        direction = new Vector2(attackerPosition.x - defenderPosition.x, attackerPosition.y - defenderPosition.y).nor();
    }

    private void checkIfReachedTarget(){
        Vector2 defenderPosition = defender.getComponent(TransformComponent.class).position;
        Vector2 attackerPosition = attacker.getComponent(TransformComponent.class).position;

        java.awt.Rectangle attackerHitBox = new Rectangle((int) attackerPosition.x, (int) attackerPosition.y, TILE_SIZE / 2 , TILE_SIZE / 2);

        if(attackerHitBox.contains(new Point((int) position.x,(int) position.y))){
            position.x = defenderPosition.x;
            position.y = defenderPosition.y;
            //TODO: Explosion animation
        }
    }

    public void draw(){
        calculateDirection();
        checkIfReachedTarget();

        batch.begin();
        batch.draw(ammo, position.x, position.y, ammo.getWidth(), ammo.getHeight());
        batch.end();

        position.x += direction.x * 200 * Gdx.graphics.getDeltaTime();
        position.y += direction.y * 200 * Gdx.graphics.getDeltaTime();

    }
}
