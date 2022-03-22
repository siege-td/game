package com.siegetd.game.singletons;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class ResourceLoader {
    public static ResourceLoader resourceLoaderInstance = null;

    private HashMap<String, Sound> sounds;
    private HashMap<String, Music> music;

    private HashMap<String, TextureRegion> attackers;
    private HashMap<String, TextureRegion> defenders;

    private HashMap<String, TextureRegion> maps;
    private HashMap<String, TextureRegion> projectiles;
    private HashMap<String, TextureRegion> collisions;

    public ResourceLoader(){
        sounds = new HashMap<>();
        music = new HashMap<>();

        attackers = new HashMap<>();
        defenders = new HashMap<>();

        maps = new HashMap<>();
        projectiles = new HashMap<>();
        collisions = new HashMap<>();
    }
    private static ResourceLoader getInstance(){
        if(resourceLoaderInstance == null){
            resourceLoaderInstance = new ResourceLoader();
        }
        return resourceLoaderInstance;
    }

    private Sound getSoundById(String id){
        return sounds.get(id);
    }

    private Music getMusicById(String id){
        return music.get(id);
    }

    private TextureRegion getAttackerTexture(String id){
        return attackers.get(id);
    }

    private TextureRegion getDefenderTexture(String id){
        return defenders.get(id);
    }

    private TextureRegion getMapById(String id){
        return maps.get(id);
    }

    private TextureRegion getProjectileTexture(String id){
        return projectiles.get(id);
    }

    private TextureRegion getCollisionTexture(String id){
        return collisions.get(id);
    }

    private void addSounds()


}
