/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.Camera;
import com.mak3e.laivapeli.engine.GameObject;
import com.mak3e.laivapeli.engine.Vector2;

/**
 * Water, covers 70% of earths surface
 * 
 * @author Make
 */
public class Water extends GameObject {

    private final int springsPerUnit = 100;
    private Spring[] springs;
    private float width;

    public Water(Vector2 pos, float levelWidth) {
        super(pos);
        this.width = levelWidth;
        springs = new Spring[(int) (this.width * springsPerUnit)];
        for (int i = 0; i < springs.length; i++) { // OPTIMIZE THIS
            springs[i] = new Spring();
        }
    }

    public float getHeight(float x) {
        if (x > 0f && x < width) {
            // IMPLEMENT THIS
        }
        return 0;
    }

    public float getAngle(float x) {
        return 0;
    }

    @Override
    public void update() {

    }

    @Override
    public void capture(Camera camera) {
        Vector2 pos = super.getPos();
        Vector2[] points = new Vector2[springs.length + 2];
        for (int i = 0; i < springs.length; i++) {
            points[i] = new Vector2(
                    pos.x + ((i / (float) (springs.length - 1)) * width),
                    pos.y + springs[i].getDistance()
            );
        }
        float bottom = -camera.getUnitsPerHeight() / 2 + camera.getPos().y;
        points[springs.length] = new Vector2(this.width, bottom);
        points[springs.length + 1] = new Vector2(pos.x, bottom);
        camera.drawPolygon(points);
    }

}

class Spring {

    private final float spring = 0.025f;
    private float distance = 0f;
    private float speed = 0f;

    public void update() {

    }

    public float getDistance() {
        return distance;
    }

}
