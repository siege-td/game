package com.siegetd.game.models.level;

public class SpawnRate {

    private int scorpionSpawnRate;
    private int ogreSpawnRate;
    private int ghostSpawnRate;

    public SpawnRate(int scorpionSpawnRate, int ogreSpawnRate, int ghostSpawnRate) {
        this.scorpionSpawnRate = scorpionSpawnRate;
        this.ogreSpawnRate = ogreSpawnRate;
        this.ghostSpawnRate = ghostSpawnRate;
    }

    public int getScorpionSpawnRate() {
        return scorpionSpawnRate;
    }

    public int getOgreSpawnRate() {
        return ogreSpawnRate;
    }

    public int getGhostSpawnRate() {
        return ghostSpawnRate;
    }
}
