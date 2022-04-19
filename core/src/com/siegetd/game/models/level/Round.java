package com.siegetd.game.models.level;

public class Round {

    private int numOfScorpions;
    private int numOfOgres;
    private int numOfGhosts;

    public Round(int numOfScorpions, int numOfOgres, int numOfGhosts) {
        this.numOfScorpions = numOfScorpions;
        this.numOfOgres = numOfOgres;
        this.numOfGhosts = numOfGhosts;
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
}
