/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.Camera;
import com.mak3e.laivapeli.engine.Clock;
import com.mak3e.laivapeli.engine.GameObject;
import com.mak3e.laivapeli.engine.Vector2;
import java.awt.Color;

/**
 * Water, covers 70% of earths surface.
 *
 * @author Make
 */
public class Water extends GameObject {

    private final int springsPerUnit = 10;
    private float[] springs;
    private float width;
    private float freq = 1f;
    private float waveTime = 0f;
    private Color color = Color.BLACK;

    /**
     * Constructor.
     *
     * @param pos position
     * @param levelWidth level width in units
     */
    public Water(Vector2 pos, float levelWidth) {
        super(pos);
        this.width = levelWidth;
        springs = new float[(int) (this.width * springsPerUnit) + 1];
        waveTime = 0;
    }
    
    /**
     * Constructor.
     * 
     * @param pos position
     * @param levelWidth level width in units
     * @param bg background?
     * @param freq frequency
     */
    public Water(Vector2 pos, float levelWidth, boolean bg, float freq) {
        this(pos, levelWidth);
        if (bg) {
            this.color = Color.GRAY;
        }
        this.freq = freq;
    }

    /**
     * Get height of the water at x position.
     *
     * @param x x position
     * @return height as float
     */
    public float getHeight(float x) {
        Vector2 height = getClosestSpringHeights(x);
        return heightBetween(height.x, height.y, x - (float) Math.floor(x));
    }

    /**
     * Get angle of the water at x position.
     *
     * @param x x position
     * @return angle in degrees as float
     */
    public float getAngle(float x) {
        Vector2 height = getClosestSpringHeights(x);
        return angleOf(height.x - height.y, 1f / springsPerUnit);
    }

    Vector2 getClosestSpringHeights(float x) {
        if (inRange(x)) {
            x *= springsPerUnit;
            int leftSpring = (int) Math.floor(x);
            int rightSpring = (int) Math.ceil(x);
            if (leftSpring == rightSpring) {
                if (leftSpring > 0) {
                    leftSpring -= 1;
                } else {
                    rightSpring += 1;
                }
            }
            float height1 = springs[leftSpring];
            float height2 = springs[rightSpring];
            return new Vector2(height1, height2);
        }
        return new Vector2(0, 0);
    }

    boolean inRange(float x) {
        return (x >= 0f && x <= width);
    }

    float angleOf(float height, float width) {
        return (float) Math.toDegrees(Math.atan(height / width));
    }

    float heightBetween(float height1, float height2, float x) {
        return (height1 * x + height2 * (1 - x));
    }

    @Override
    public void update() {
        float deltaTime = Clock.time.getDeltaTime();
        for (int i = 0; i < springs.length; i++) {
            float waves = 0f;
            waves += Math.sin(i / (double) springs.length * width * freq + waveTime) * 0.05f;
            waves += Math.sin(i / (double) springs.length + 1 * width * freq + waveTime) * 0.1f;
            springs[i] = waves;
        }
        waveTime += deltaTime;
    }

    Vector2[] getPoints(Camera camera) {
        Vector2 pos = super.getPos();
        Vector2[] points = new Vector2[springs.length + 2];
        for (int i = 0; i < springs.length; i++) {
            points[i] = new Vector2(
                    pos.x + ((i / (float) (springs.length - 1)) * width),
                    pos.y + springs[i]
            );
        }
        float bottom = -camera.getUnitsPerHeight() / 2 + camera.getPos().y - 1;
        points[springs.length] = new Vector2(this.width, bottom);
        points[springs.length + 1] = new Vector2(pos.x, bottom);
        return points;
    }

    @Override
    public void capture(Camera camera) {
        camera.drawPolygon(getPoints(camera), color);
    }

    @Override
    public void debug(Camera camera) {
        camera.drawPolygonDebug(getPoints(camera));
    }
}
