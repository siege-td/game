package com.siegetd.game.controllers;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.siegetd.game.EngineState;
import com.siegetd.game.models.ecs.components.CharacteristicsComponent;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.models.ecs.components.Type;
import com.siegetd.game.models.ecs.components.TypeComponent;
import com.siegetd.game.models.ecs.entities.defender.Defender;

import java.awt.Point;
import java.awt.Rectangle;

public class DefendAttackPair {
    Entity attacker;
    Entity defender;

    private Texture ammo;


    private Vector2 bulletPosition;
    private Vector2 bulletDirection;

    private Boolean intersects;
    private Vector2 attackerPosition;
    private Vector2 defenderPosition;

    private int tower_radius;


    public DefendAttackPair(Entity defender, Entity attacker){
        this.attacker = attacker;
        this.defender = defender;

        this.attackerPosition = attacker.getComponent(TransformComponent.class).position;
        this.defenderPosition = defender.getComponent(TransformComponent.class).position;

        this.bulletPosition = new Vector2(defender.getComponent(TransformComponent.class).position.x, defender.getComponent(TransformComponent.class).position.y);
        this.tower_radius = defender.getComponent(CharacteristicsComponent.class).tower_radius;


        checkIfIntersects();
        setProjectile();
    }

    private void setProjectile(){

        switch (defender.getComponent(TypeComponent.class).defenderType){
            case MAGE:
                this.ammo = new Texture("projectile/mage_projectile.png");
                break;
            case ARCHER:
                this.ammo = new Texture("projectile/wrecking_ball.png");
                break;
            case ZAPP:
                this.ammo = new Texture("projectile/zapp_projectile.png");
                break;

        }
    }

    private void checkIfIntersects(){
        java.awt.Rectangle towerRect = new Rectangle((int) defenderPosition.x - (tower_radius / 2), (int) defenderPosition.y - (tower_radius / 2), tower_radius , tower_radius);
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
            //TODO: Decrease unit health
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



            bulletPosition.x += (bulletDirection.x * defender.getComponent(CharacteristicsComponent.class).attack_speed) * Gdx.graphics.getDeltaTime();
            bulletPosition.y += (bulletDirection.y * defender.getComponent(CharacteristicsComponent.class).attack_speed) * Gdx.graphics.getDeltaTime();
        }

    }
}
