package com.siegetd.game.controllers.collision;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.siegetd.game.EngineState;
import com.siegetd.game.models.ecs.components.CharacteristicsComponent;
import com.siegetd.game.models.ecs.components.HitpointComponent;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.models.ecs.components.TypeComponent;


public class DefendAttackPair {

    private Entity attacker;
    private Entity defender;

    private Texture ammo;

    private Vector2 bulletPosition;
    private Vector2 bulletDirection;

    private Vector2 attackerPosition;
    private Vector2 defenderPosition;

    private int defenderRadius;

    public DefendAttackPair(Entity defender, Entity attacker){
        this.attacker = attacker;
        this.defender = defender;

        this.attackerPosition = attacker.getComponent(TransformComponent.class).position;
        this.defenderPosition = defender.getComponent(TransformComponent.class).position;

        this.bulletPosition = new Vector2(defender.getComponent(TransformComponent.class).position.x, defender.getComponent(TransformComponent.class).position.y);
        this.defenderRadius = defender.getComponent(CharacteristicsComponent.class).tower_radius;

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

    private boolean checkIfIntersects(){
        Rectangle towerRect = new Rectangle((int) defenderPosition.x - (defenderRadius / 2), (int) defenderPosition.y - (defenderRadius / 2), defenderRadius , defenderRadius);
        Rectangle attackerRect = new Rectangle((int) attackerPosition.x - (TILE_SIZE / 2), (int) attackerPosition.y - (TILE_SIZE / 2), TILE_SIZE , TILE_SIZE);

        return Intersector.intersectRectangles(towerRect, attackerRect, towerRect);
    }

    private void calculateDirection(){
        this.bulletDirection = new Vector2(attackerPosition.x - defenderPosition.x, attackerPosition.y - defenderPosition.y).nor();
    }

    private void checkIfReachedTarget(){
        Rectangle attackerHitBox = new Rectangle((int) attackerPosition.x - (TILE_SIZE / 2), (int) attackerPosition.y - (TILE_SIZE / 2), TILE_SIZE / 2 , TILE_SIZE / 2);

        if(Intersector.overlaps(new Circle((int) bulletPosition.x, (int) bulletPosition.y, 1), attackerHitBox)) {
            this.bulletPosition.x = defenderPosition.x;
            this.bulletPosition.y = defenderPosition.y;
            attacker.getComponent(HitpointComponent.class).hitpoints -= defender.getComponent(CharacteristicsComponent.class).attack_damage;
            if(attacker.getComponent(HitpointComponent.class).hitpoints <= 0){
                EngineState.ecsEngine.removeEntity(attacker);
            }
        }
    }

    public void draw(){
        calculateDirection();

        if(checkIfIntersects()){
            checkIfReachedTarget();

            EngineState.batch.begin();
            EngineState.batch.draw(ammo, bulletPosition.x, bulletPosition.y, ammo.getWidth(), ammo.getHeight());
            EngineState.batch.end();

            bulletPosition.x += (bulletDirection.x * defender.getComponent(CharacteristicsComponent.class).attack_speed) * Gdx.graphics.getDeltaTime();
            bulletPosition.y += (bulletDirection.y * defender.getComponent(CharacteristicsComponent.class).attack_speed) * Gdx.graphics.getDeltaTime();
        }
    }

    public Entity getAttacker() {
        return attacker;
    }

    public Entity getDefender() {
        return defender;
    }
}
