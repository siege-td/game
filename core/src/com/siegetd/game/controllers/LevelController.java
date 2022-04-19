package com.siegetd.game.controllers;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.siegetd.game.models.ecs.EntitySpawner;
import com.siegetd.game.models.ecs.entities.attacker.Attacker;
import com.siegetd.game.models.level.Level;
import com.siegetd.game.models.level.Round;
import com.siegetd.game.models.level.SpawnRate;

import java.util.ArrayList;

public class LevelController {

    private Level levelData;

    public LevelController(int level) {
        loadData(level);
    }

    public void startRound(int round, PooledEngine engine) {
        EntitySpawner entitySpawner = new EntitySpawner(engine);

        Round currentRound = levelData.getRounds().get(round);

        entitySpawner.spawnAttackerAtInterval(
                1,
                Attacker.SCORPION,
                currentRound.getNumOfScorpions(),
                levelData.getEntitySpawnPos()
        );
    }

    private void loadData(int level) {
        JsonReader reader = new JsonReader();
        JsonValue jsonValue = reader.parse(Gdx.files.internal("level" + level + "/level.json"));

        JsonValue entitySpawnPos = jsonValue.get("spawnPos");
        JsonValue entityEndPos = jsonValue.get("endPos");

        Vector2 spawnPos = new Vector2(
                entitySpawnPos.getFloat(0),
                entitySpawnPos.getFloat(1)
        );
        Vector2 endPos = new Vector2(
                entityEndPos.getFloat(0),
                entityEndPos.getFloat(1)
        );

        JsonValue jsonRounds = jsonValue.get("rounds");

        ArrayList<Round> rounds = new ArrayList<>();
        if (jsonRounds != null) {
            for (int i = 0; i < jsonRounds.size; i++) {
                int currNumScorpions = jsonRounds.get(i).getInt(1);
                int currNumOgres = jsonRounds.get(i).getInt(2);
                int currNumGhosts = jsonRounds.get(i).getInt(3);

                SpawnRate spawnRate = new SpawnRate(
                        jsonRounds.get(i).get("spawnRates").getInt(0),
                        jsonRounds.get(i).get("spawnRates").getInt(1),
                        jsonRounds.get(i).get("spawnRates").getInt(2)
                );

                rounds.add(new Round(currNumScorpions, currNumOgres, currNumGhosts, spawnRate));
            }
        }

        this.levelData = new Level(spawnPos, endPos, rounds);
    }
}
