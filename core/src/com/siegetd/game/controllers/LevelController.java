package com.siegetd.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.siegetd.game.models.level.Level;
import com.siegetd.game.models.level.Round;

import java.util.ArrayList;

public class LevelController {

    private int level;
    private int currentRoundOfLevel;

    private Level levelData;

    public LevelController(int level) {
        this.level = level;

        loadData();
    }

    private void loadData() {
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

                rounds.add(new Round(currNumScorpions, currNumOgres, currNumGhosts));
            }
        }

        this.levelData = new Level(spawnPos, endPos, rounds);
    }
}
