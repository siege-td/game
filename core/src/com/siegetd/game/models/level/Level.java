package com.siegetd.game.models.level;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class Level {

    private Vector2 entitySpawnPos;
    private Vector2 entityEndPos;

    private List<Round> rounds;

    public Level(Vector2 entitySpawnPos, Vector2 entityEndPos, List<Round> rounds) {
        this.entitySpawnPos = entitySpawnPos;
        this.entityEndPos = entityEndPos;

        this.rounds = rounds;
    }

    public Vector2 getEntitySpawnPos() {
        return entitySpawnPos;
    }

    public Vector2 getEntityEndPos() {
        return entityEndPos;
    }

    public List<Round> getRounds() {
        return rounds;
    }
}
