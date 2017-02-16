/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.Camera;
import com.mak3e.laivapeli.engine.Core;
import com.mak3e.laivapeli.engine.GameObject;
import com.mak3e.laivapeli.engine.Vector2;

/**
 * Water, covers 70% of earths surface
 *
 * @author Make
 */
public class Water extends GameObject {

    private final int springsPerUnit = 10;
    private Spring[] springs;
    private float width;
    private final float tension = 0.025f;
    private final float dampening = 0.025f;
    private final float spread = 0.1f;
    private float stepTime = 0f;
    private float stepsPerSecond = 30f;
    private float stepDelta = 1f / stepsPerSecond;

    public Water(Vector2 pos, float levelWidth) {
        super(pos);
        this.width = levelWidth;
        springs = new Spring[(int) (this.width * springsPerUnit)];
        for (int i = 0; i < springs.length; i++) { // OPTIMIZE THIS
            springs[i] = new Spring();
        }
        springs[1].height = -10f;
    }

    /**
     * Get height of the water at x position
     *
     * @param x x position
     * @return height as float
     */
    public float getHeight(float x) {
        if (inRange(x)) {
            x *= springsPerUnit;
            int leftSpring = (int) Math.floor(x);
            int rightSpring = (int) Math.ceil(x);
            float height1 = springs[leftSpring].height;
            float height2 = springs[rightSpring].height;
            return heightBetween(height1, height2, x - leftSpring);
        }
        return 0;
    }

    /**
     * Get angle of the water at x position
     *
     * @param x x position
     * @return angle in degrees as float
     */
    public float getAngle(float x) {
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
            float height1 = springs[leftSpring].height;
            float height2 = springs[rightSpring].height;
            return angleOf(height1 - height2, 1f / springsPerUnit);
        }
        return 0;
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
        float deltaTime = Core.engine.getClock().getDeltaTime();
        stepTime += deltaTime;
        while (stepTime > stepDelta) {
            for (int i = 0; i < springs.length; i++) {
                springs[i].step(dampening, tension);
            }
            float[] leftDeltas = new float[springs.length];
            float[] rightDeltas = new float[springs.length];
            for (int j = 0; j < 8; j++) {
                for (int i = 0; i < springs.length; i++) {
                    if (i > 0) {
                        leftDeltas[i] = spread
                                * (springs[i].height - springs[i - 1].height);
                        springs[i - 1].velocity += leftDeltas[i];
                    }
                    if (i < springs.length - 1) {
                        rightDeltas[i] = spread
                                * (springs[i].height - springs[i + 1].height);
                        springs[i + 1].velocity += rightDeltas[i];
                    }
                }
                for (int i = 0; i < springs.length; i++) {
                    if (i > 0) {
                        springs[i - 1].height += leftDeltas[i];
                    }
                    if (i < springs.length - 1) {
                        springs[i + 1].height += rightDeltas[i];
                    }
                }
            }
            stepTime -= stepDelta;
        }
        for (int i = 0; i < springs.length; i++) {
            springs[i].update(deltaTime * stepsPerSecond);
        }
    }

    @Override
    public void capture(Camera camera) {
        Vector2 pos = super.getPos();
        Vector2[] points = new Vector2[springs.length + 2];
        for (int i = 0; i < springs.length; i++) {
            points[i] = new Vector2(
                    pos.x + ((i / (float) (springs.length - 1)) * width),
                    pos.y + springs[i].height
            );
        }
        float bottom = -camera.getUnitsPerHeight() / 2 + camera.getPos().y - 1;
        points[springs.length] = new Vector2(this.width, bottom);
        points[springs.length + 1] = new Vector2(pos.x, bottom);
        camera.drawPolygon(points);
    }
}

class Spring {

    public float height = 0f;
    public float velocity = 0f;

    public void update(float deltaStep) {
        height += velocity * deltaStep;
    }

    public void step(float dampening, float tension) {
        velocity += -height * tension - velocity * dampening;
    }

}
