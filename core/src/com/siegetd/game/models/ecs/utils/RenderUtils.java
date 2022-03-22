package com.siegetd.game.models.ecs.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class RenderUtils {
    private float pixelsPerMeter = 0f;
    private float pixelsToMeters = 0f;

    private float frustumWidth = 0f;
    private float frustumHeight = 0f;

    public RenderUtils() {
        pixelsPerMeter = 32f;
        pixelsToMeters = 1f / pixelsPerMeter;

        frustumWidth = Gdx.graphics.getWidth() / pixelsPerMeter;
        frustumHeight = Gdx.graphics.getHeight() / pixelsPerMeter;
    }

    public Vector2 getScreenSizeInMeters() {
        return new Vector2(
                Gdx.graphics.getWidth() * pixelsToMeters,
                Gdx.graphics.getHeight() * pixelsToMeters
        );
    }

    public Vector2 getScreenSizeInPixels() {
        return new Vector2(
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight()
        );
    }

    public float convertPixelsToMeters(float pixels) {
        return pixels * pixelsToMeters;
    }

    public float getFrustumWidth() {
        return frustumWidth;
    }

    public void setFrustumWidth(float frustumWidth) {
        this.frustumWidth = frustumWidth;
    }

    public float getFrustumHeight() {
        return frustumHeight;
    }

    public void setFrustumHeight(float frustumHeight) {
        this.frustumHeight = frustumHeight;
    }
}
