package com.siegetd.game.models.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class TurretComponent implements Component {
    public Entity target;
    public Sound turretSound = Gdx.audio.newSound(Gdx.files.internal("sounds/IceShot.mp3"));
}
