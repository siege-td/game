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
        return this.sounds.get(id);
    }

    private Music getMusicById(String id){
        return this.music.get(id);
    }

    private TextureRegion getAttackerTexture(String id){
        return this.attackers.get(id);
    }

    private TextureRegion getDefenderTexture(String id){
        return this.defenders.get(id);
    }

    private TextureRegion getMapById(String id){
        return this.maps.get(id);
    }

    private TextureRegion getProjectileTexture(String id){
        return this.projectiles.get(id);
    }

    private TextureRegion getCollisionTexture(String id){
        return this.collisions.get(id);
    }

    private void addSound(String id,Sound sound){
        this.sounds.put(id,sound);
    }

    private void addMusic(String id,Music music){
        this.music.put(id,music);
    }

    private void addAttacker(String id,TextureRegion attacker){
        this.attackers.put(id,attacker);
    }

    private void addDefender(String id, TextureRegion defender){
        this.defenders.put(id,defender);
    }

    private void addMap(String id,TextureRegion map){
        this.maps.put(id,map);
    }

    private void addProjectile(String id, TextureRegion projectile){
        this.projectiles.put(id,projectile);
    }

    private void addCollisions(String id, TextureRegion collision){
        this.collisions.put(id,collision);
    }

}
