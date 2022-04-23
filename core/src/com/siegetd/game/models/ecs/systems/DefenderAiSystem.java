package com.siegetd.game.models.ecs.systems;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
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
import com.siegetd.game.models.ecs.components.Type;
import com.siegetd.game.models.ecs.components.TypeComponent;
import com.siegetd.game.models.ecs.components.VelocityComponent;

public class DefenderAiSystem extends IteratingSystem {

    private Entity target;
    private Entity defender;

    private Texture ammo;

    private Vector2 bulletPosition;
    private Vector2 bulletDirection;

    private Vector2 targetPosition;
    private Vector2 defenderPosition;

    private int defenderRadius;

    public DefenderAiSystem() {
        super(Family.all(TypeComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(entity.getComponent(TypeComponent.class).type == Type.ATTACKER){
            return;
        }
        this.defender = entity;

        this.defenderPosition = defender.getComponent(TransformComponent.class).position;

        this.bulletPosition = new Vector2(defender.getComponent(TransformComponent.class).position.x, defender.getComponent(TransformComponent.class).position.y);
        this.defenderRadius = defender.getComponent(CharacteristicsComponent.class).tower_radius;

        setProjectile();
        findBestTarget();
        draw();
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

    public void findBestTarget(){
        Rectangle towerRect = new Rectangle((int) defenderPosition.x - (defenderRadius / 2), (int) defenderPosition.y - (defenderRadius / 2), defenderRadius , defenderRadius);
        for (Entity entity : EngineState.ecsEngine.getEntities()) {
            // If entity is not attacker return
            if (entity.getComponent(TypeComponent.class).type != Type.ATTACKER){
                return;
            }

            Vector2 attackerPos = entity.getComponent(TransformComponent.class).position;
            Rectangle attackerRect = new Rectangle((int) attackerPos.x, (int) attackerPos.y, TILE_SIZE, TILE_SIZE);
            // If attacker is not in tower range return


            if(!towerRect.contains(attackerPos)){
                return;
            }

            if(target==null){
                System.out.println(towerRect);
                System.out.println(attackerPos);
                target = entity;
                targetPosition = attackerPos;
                return;
            }

            System.out.println(attackerPos.dst2(EngineState.gameMap.getEndPosition()));
            // Check if attacker pos is closer to end tile than currently target enemy change current target
            if(attackerPos.dst2(EngineState.gameMap.getEndPosition()) < target.getComponent(TransformComponent.class).position.dst2(EngineState.gameMap.getEndPosition())){
                System.out.println(towerRect);
                target = entity;
                targetPosition = attackerPos;
            }
        }
    }

    private void calculateDirection(){
        this.bulletDirection = new Vector2(targetPosition.x - defenderPosition.x, targetPosition.y - defenderPosition.y).nor();
    }

    private void checkIfReachedTarget(){
        Rectangle attackerHitBox = new Rectangle((int) targetPosition.x - (TILE_SIZE / 2), (int) targetPosition.y - (TILE_SIZE / 2), TILE_SIZE / 2 , TILE_SIZE / 2);

        if(Intersector.overlaps(new Circle((int) bulletPosition.x, (int) bulletPosition.y, 2), attackerHitBox)) {
            this.bulletPosition.x = defenderPosition.x;
            this.bulletPosition.y = defenderPosition.y;
            if(target.getComponent(HitpointComponent.class) == null) {
                return;
            }
            target.getComponent(HitpointComponent.class).hitpoints -= defender.getComponent(CharacteristicsComponent.class).attack_damage;
            if(target.getComponent(HitpointComponent.class).hitpoints <= 0){
                EngineState.ecsEngine.removeEntity(target);
                target = null;
                targetPosition = null;
            }
        }
    }

    public void draw(){
        if(target == null){
            return;
        }
        calculateDirection();
        checkIfReachedTarget();

        EngineState.batch.begin();
        EngineState.batch.draw(ammo, bulletPosition.x, bulletPosition.y, ammo.getWidth(), ammo.getHeight());
        EngineState.batch.end();

        bulletPosition.x += (bulletDirection.x * defender.getComponent(CharacteristicsComponent.class).attack_speed) * Gdx.graphics.getDeltaTime();
        bulletPosition.y += (bulletDirection.y * defender.getComponent(CharacteristicsComponent.class).attack_speed) * Gdx.graphics.getDeltaTime();
    }

    public Entity getTarget() {
        return target;
    }

    public Entity getDefender() {
        return defender;
    }
}
