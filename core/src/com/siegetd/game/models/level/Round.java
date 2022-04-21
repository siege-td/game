package com.siegetd.game.models.level;

public class Round {

    private int numOfScorpions;
    private int numOfOgres;
    private int numOfGhosts;
    private SpawnRate spawnRate;

    public Round(int numOfScorpions, int numOfOgres, int numOfGhosts, SpawnRate spawnRate) {
        this.numOfScorpions = numOfScorpions;
        this.numOfOgres = numOfOgres;
        this.numOfGhosts = numOfGhosts;
        this.spawnRate = spawnRate;
    }

    public int getNumOfScorpions() {
        return numOfScorpions;
    }

    public int getNumOfOgres() {
        return numOfOgres;
    }

    public int getNumOfGhosts() {
        return numOfGhosts;
    }

    public SpawnRate getSpawnRate() {
        return spawnRate;
    }
}
