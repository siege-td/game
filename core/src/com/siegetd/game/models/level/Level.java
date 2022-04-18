package com.siegetd.game.models.level;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Level {

    private Vector2 entitySpawnPos;
    private Vector2 entityEndPos;

    private ArrayList<Round> rounds;

    public Level(Vector2 entitySpawnPos, Vector2 entityEndPos) {
        this.entitySpawnPos = entitySpawnPos;
        this.entityEndPos = entityEndPos;

        this.rounds = new ArrayList<>();
    }

    public Vector2 getEntitySpawnPos() {
        return entitySpawnPos;
    }

    public Vector2 getEntityEndPos() {
        return entityEndPos;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }
}
