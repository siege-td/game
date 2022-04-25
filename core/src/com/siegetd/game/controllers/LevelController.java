package com.siegetd.game.controllers;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Timer;
import com.siegetd.game.SiegeTd;
import com.siegetd.game.SiegeTdState;
import com.siegetd.game.api.SocketConnection;
import com.siegetd.game.models.ecs.EntitySpawner;
import com.siegetd.game.models.ecs.components.AttackerComponent;
import com.siegetd.game.models.ecs.components.TransformComponent;
import com.siegetd.game.models.ecs.components.TypeComponent;
import com.siegetd.game.models.ecs.entities.attacker.Attacker;
import com.siegetd.game.models.level.Level;
import com.siegetd.game.models.level.Round;
import com.siegetd.game.models.level.SpawnRate;

import java.net.Socket;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.print.URIException;

import io.socket.emitter.Emitter;

public class LevelController {

    private Level levelData;
    private int currRound = 0;
    private boolean roundOngoing = false;
    private int count = 1;

    public LevelController(int level) {
        loadData(level);
        try {
            SocketConnection.getInstance().getSocket().on("next_round", onNextRound);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void startRound() {
        // Wait for attackers to be deleted
        new Timer().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                EntitySpawner entitySpawner = new EntitySpawner();

                Round currentRound = levelData.getRounds().get(currRound);

                // TODO: ITERATE OVER ALL INSTEAD OF USING IFS, INCREASES MODIFIABILITY
                // Spawn scorpions
                if (currentRound.getSpawnRate().getScorpionSpawnRate() > 0) {
                    entitySpawner.spawnAttackerAtInterval(
                            currentRound.getSpawnRate().getScorpionSpawnRate(),
                            Attacker.SCORPION,
                            currentRound.getNumOfScorpions(),
                            levelData.getEntitySpawnPos()
                    );
                }
                // Spawn ogres
                if (currentRound.getSpawnRate().getOgreSpawnRate() > 0) {
                    entitySpawner.spawnAttackerAtInterval(
                            currentRound.getSpawnRate().getOgreSpawnRate(),
                            Attacker.OGRE,
                            currentRound.getNumOfOgres(),
                            levelData.getEntitySpawnPos()
                    );
                }
                // Spawn ghosts
                if (currentRound.getSpawnRate().getGhostSpawnRate() > 0) {
                    entitySpawner.spawnAttackerAtInterval(
                            currentRound.getSpawnRate().getGhostSpawnRate(),
                            Attacker.GHOST,
                            currentRound.getNumOfOgres(),
                            levelData.getEntitySpawnPos()
                    );
                }
                currRound++;
                roundOngoing = false;
            }
        }, 7, 1, 0);
    }
    // TODO: ADD handler for when level is done

    public void isRoundFinished() {
        ImmutableArray<Entity> attackers = SiegeTdState.ecsEngine.getEntitiesFor(Family.all(AttackerComponent.class).get());
        ArrayList<Entity> attackersAtEnd = new ArrayList<>();
        count++;

        for (Entity attacker : attackers) {
            if ((attacker.getComponent(TransformComponent.class).position.y >= levelData.getEntityEndPos().y) && (attacker.getComponent(TransformComponent.class).position.x >= levelData.getEntityEndPos().x)) {
                attackersAtEnd.add(attacker);
            }
        }
        if (attackers.size() == attackersAtEnd.size() && !roundOngoing && count%60 == 0) {
            try {
                SocketConnection.getInstance().getSocket().emit("test");
                SocketConnection.getInstance().getSocket().emit("next_round", SiegeTdState.pin);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            roundOngoing = true;
        }

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
    private Emitter.Listener onNextRound = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            startRound();
        }
    };
}
