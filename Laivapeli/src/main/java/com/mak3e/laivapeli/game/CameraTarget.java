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
 * CameraTarget follows game objects and makes camera follow it
 *
 * @author Make
 */
public class CameraTarget extends GameObject {

    private GameObject target;
    private Camera camera;
    private float levelWidth;

    public CameraTarget(Vector2 pos, float levelWidth) {
        super(pos);
        this.levelWidth = levelWidth;
    }

    @Override
    public void update() {
        if (target != null) {
            super.setPos(nextPos(target.getPos(), camera.getUnitsPerWidth()));
        }
    }

    Vector2 nextPos(Vector2 pos, float viewWidth) {
        float xMin, xMax;
        xMin = viewWidth / 2;
        xMax = levelWidth - (viewWidth / 2);
        return new Vector2(Math.max(xMin, Math.min(xMax, pos.x)), pos.y);
    }

    /**
     * Set camera that must follow this
     *
     * @param camera Camera
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
        this.camera.setTarget(this);
    }

    /**
     * Set target that this will follow
     *
     * @param target target GameObject
     */
    public void followTarget(GameObject target) {
        this.target = target;
    }

}
