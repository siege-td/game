package com.siegetd.game.models.ecs.systems;


import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.siegetd.game.SiegeTdState;
import com.siegetd.game.models.ecs.components.AttackerComponent;
import com.siegetd.game.models.ecs.components.CharacteristicsComponent;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.models.ecs.components.TurretComponent;
import com.siegetd.game.models.ecs.components.TypeComponent;
import com.siegetd.game.models.ecs.entities.ammo.BulletEntity;

import java.util.ArrayList;

public class DefenderAiSystem extends IteratingSystem {

    private Entity defender;
    private Vector2 defenderPosition;
    private int defenderRadius;

    private Texture bullet_texture;
    private Vector2 bulletDirection;

    private ComponentMapper<CharacteristicsComponent> characteristicMapper;

    public DefenderAiSystem() {
        super(Family.all(TurretComponent.class).get());

        characteristicMapper = ComponentMapper.getFor(CharacteristicsComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        this.defender = entity;

        this.defenderPosition = defender.getComponent(TransformComponent.class).position;

        this.defenderRadius = defender.getComponent(CharacteristicsComponent.class).towerRadius;

        setProjectile();
        shootEnemy(deltaTime);
        resetTarget();
    }

    private void resetTarget(){
        defender.getComponent(TurretComponent.class).target = null;
    }


    private void shootEnemy(float deltaTime){
        this.defender.getComponent(CharacteristicsComponent.class).shotTimer += deltaTime;
        if(this.defender.getComponent(CharacteristicsComponent.class).shotTimer > defender.getComponent(CharacteristicsComponent.class).attackRate){
            findBestTarget();
            if(defender.getComponent(TurretComponent.class).target != null) {
                calculateDirection();

                CharacteristicsComponent defenderStats = characteristicMapper.get(defender);

                BulletEntity bullet = new BulletEntity(bullet_texture, (int) defenderPosition.x + TILE_SIZE/2, (int) defenderPosition.y + TILE_SIZE/2, bulletDirection.x, bulletDirection.y, defenderStats.attackDamage);
                SiegeTdState.ecsEngine.addEntity(bullet);

                this.defender.getComponent(CharacteristicsComponent.class).shotTimer = 0;
            }
        }
    }


    private void setProjectile(){
        switch (defender.getComponent(TypeComponent.class).defenderType){
            case MAGE:
                this.bullet_texture = new Texture("projectile/mage_projectile.png");
                break;
            case ARCHER:
                this.bullet_texture = new Texture("projectile/wrecking_ball.png");
                break;
            case ZAPP:
                this.bullet_texture = new Texture("projectile/zapp_projectile.png");
                break;
        }
    }

    private void findBestTarget(){
        Rectangle towerRect = new Rectangle((int) defenderPosition.x - (defenderRadius / 2), (int) defenderPosition.y - (defenderRadius / 2), defenderRadius , defenderRadius);
        ImmutableArray<Entity> attackers = SiegeTdState.ecsEngine.getEntitiesFor(Family.all(AttackerComponent.class).get());
        ArrayList<Entity> enemiesInRange = new ArrayList<>();

        //Get all attackers in range
        for (Entity attacker : attackers) {
            Vector2 attackerPos = attacker.getComponent(TransformComponent.class).position;
            if (towerRect.contains(attackerPos)) {
                enemiesInRange.add(attacker);
            }
        }

        // If no attackers are in turret range return
        if(enemiesInRange.isEmpty()) {
            return;
        }

        // Loop through attackers in range to see how is closest to end
        for(Entity attacker : enemiesInRange) {
            Vector2 attackerPos = attacker.getComponent(TransformComponent.class).position;
            // if current target is null set to first attacker
            if (defender.getComponent(TurretComponent.class).target == null) {
                defender.getComponent(TurretComponent.class).target = attacker;
                return;
            }
            Entity target = defender.getComponent(TurretComponent.class).target;
            // Check if attacker pos is closer to end tile than currently target enemy change current target
            if (attackerPos.dst2(SiegeTdState.gameMap.getEndPosition()) < target.getComponent(TransformComponent.class).position.dst2(SiegeTdState.gameMap.getEndPosition())) {
                defender.getComponent(TurretComponent.class).target = attacker;
            }
        }
    }

    private void calculateDirection(){
        Entity target = defender.getComponent(TurretComponent.class).target;
        Vector2 targetPosition = target.getComponent(TransformComponent.class).position;
        this.bulletDirection = new Vector2(targetPosition.x - defenderPosition.x, targetPosition.y - defenderPosition.y).nor();
    }
}
