package com.siegetd.game.models.ecs.systems;


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

public class DefenderAiSystem extends IteratingSystem {

    private Entity target;
    private Entity defender;

    private Texture bullet_texture;
    private Vector2 bulletDirection;

    private Vector2 targetPosition;
    private Vector2 defenderPosition;

    private int defenderRadius;

    private ComponentMapper<CharacteristicsComponent> characteristicMapper;

    public DefenderAiSystem() {
        super(Family.all(TurretComponent.class).get());

        characteristicMapper = ComponentMapper.getFor(CharacteristicsComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        this.defender = entity;

        this.defenderPosition = defender.getComponent(TransformComponent.class).position;

        this.defenderRadius = defender.getComponent(CharacteristicsComponent.class).tower_radius;

        setProjectile();
        shootEnemy(deltaTime);
        resetEnemy();
    }

    private void resetEnemy(){
        target = null;
        targetPosition = null;
        this.defender = null;
    }

    private void shootEnemy(float deltaTime){
        this.defender.getComponent(CharacteristicsComponent.class).shotTimer += deltaTime;
        if(this.defender.getComponent(CharacteristicsComponent.class).shotTimer > defender.getComponent(CharacteristicsComponent.class).attack_rate){
            findBestTarget();
            if(target != null) {
                calculateDirection();

                CharacteristicsComponent defenderStats = characteristicMapper.get(defender);

                BulletEntity bullet = new BulletEntity(bullet_texture, (int) defenderPosition.x, (int) defenderPosition.y, bulletDirection.x, bulletDirection.y, defenderStats.attack_damage);
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
        for (Entity attacker : attackers) {

            Vector2 attackerPos = attacker.getComponent(TransformComponent.class).position;
            if(!towerRect.contains(attackerPos)){
                return;
            }

            // if current target is null set to first attacker
            if(target==null){
                target = attacker;
                targetPosition = attackerPos;
                return;
            }

            // Check if attacker pos is closer to end tile than currently target enemy change current target
            if(attackerPos.dst2(SiegeTdState.gameMap.getEndPosition()) < target.getComponent(TransformComponent.class).position.dst2(SiegeTdState.gameMap.getEndPosition())){
                target = attacker;
                targetPosition = attackerPos;
            }
        }
    }

    private void calculateDirection(){
        this.bulletDirection = new Vector2(targetPosition.x - defenderPosition.x, targetPosition.y - defenderPosition.y).nor();
    }
}
