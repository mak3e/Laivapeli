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
 * CameraTarget, follows game objects and makes camera follow it
 * 
 * @author Make
 */
public class CameraTarget extends GameObject {

    private GameObject target;
    private Camera camera;

    public CameraTarget(Vector2 pos, float levelWidth) {
        super(pos);
    }

    @Override
    public void update() {
        if (target != null) {
            
        }
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
        this.camera.setTarget(this);
    }

    public void followTarget(GameObject target) {
        this.target = target;
    }

}
