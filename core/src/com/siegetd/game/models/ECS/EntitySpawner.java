package com.siegetd.game.models.ecs;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.siegetd.game.models.ecs.entities.attacker.Attacker;
import com.siegetd.game.models.ecs.entities.attacker.GhostEntity;
import com.siegetd.game.models.ecs.entities.attacker.OgreEntity;
import com.siegetd.game.models.ecs.entities.attacker.ScorpionEntity;

public class EntitySpawner {

    private PooledEngine engine;

    public EntitySpawner(PooledEngine engine) {
        this.engine = engine;
    }

    public void spawnAttackerAtInterval(
            int intervalInSec,
            final Attacker attackerType,
            int numOfAttackers,
            final Vector2 spawnPos
    ) {
        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                switch (attackerType) {
                    case SCORPION:
                        new ScorpionEntity(engine, spawnPos, new Vector2(200f, 200f)).create();
                        break;
                    case OGRE:
                        new OgreEntity(engine, spawnPos, new Vector2(100f, 100f)).create();
                        break;
                    case GHOST:
                        new GhostEntity(engine, spawnPos, new Vector2(300f, 300f)).create();
                        break;
                }
            }
        }, 0, intervalInSec, numOfAttackers-1);
    }
}
