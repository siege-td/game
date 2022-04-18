package com.siegetd.game.controllers;

import com.siegetd.game.models.level.Level;

public class LevelController {

    private int level;
    private int currentRoundOfLevel;

    private Level levelData;

    public LevelController(int level) {
        this.level = level;

        loadData();
    }

    private void loadData() {

    }
}
