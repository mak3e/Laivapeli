/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.GameObject;
import com.mak3e.laivapeli.engine.Vector2;


/**
 *
 * @author Make
 */
public class Water extends GameObject {

    private float width;
    
    public Water(Vector2 pos, int layer, float width) {
        super(pos, layer);
        this.width = width;
    }
    
    public float getHeight(float x) {
        return 0;
    }
    
    public float getAngle(float x) {
        return 0;
    }
}
