package com.siegetd.game.controllers;

import static com.siegetd.game.models.map.utils.MapGlobals.TILE_COLUMN;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_ROW;
import static com.siegetd.game.models.map.utils.MapGlobals.TILE_SIZE;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.siegetd.game.models.ECS.components.TransformComponent;

public class DefendAttackPair {
    Entity attacker = null;
    Entity defender = null;
    int imgWidth;
    int imgHeight;
    SpriteBatch batch;
    Texture img;
    float speedX = 3f;
    float speedY = 3f;
    int x;
    int y;


    private Pixmap origScorpionImg = new Pixmap(Gdx.files.internal("ammo/stone_small.png"));
    private Pixmap scaledScorpionImg = new Pixmap(
            ((TILE_SIZE * TILE_COLUMN) / TILE_COLUMN) * 1,
            ((TILE_SIZE * TILE_ROW) / TILE_ROW) * 1,
            origScorpionImg.getFormat()
    );

    public DefendAttackPair(Entity defender, Entity attacker){
        this.attacker = attacker;
        this.defender = defender;

        this.scaledScorpionImg.drawPixmap(origScorpionImg,
                0, 0, origScorpionImg.getWidth(), origScorpionImg.getHeight(),
                0, 0, scaledScorpionImg.getWidth(), scaledScorpionImg.getHeight()
        );

        img = new Texture(scaledScorpionImg);
        x = Gdx.graphics.getWidth()/2;
        y = Gdx.graphics.getHeight()/2;
        imgWidth = img.getWidth();
        imgHeight = img.getHeight();


    }

    public void update(float delta){
        if(defender != null && attacker != null){
            batch.begin();
            batch.draw(img, x - img.getWidth()/2, y - img.getHeight()/2, imgWidth, imgHeight, 0, 0, imgWidth, imgHeight);
            batch.end();
        }
    }
}
