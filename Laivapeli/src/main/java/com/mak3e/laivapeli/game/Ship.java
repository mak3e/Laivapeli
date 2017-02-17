/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.GameObject;
import com.mak3e.laivapeli.engine.Vector2;

/**
 * Ship. Floats on water
 *
 * @author Make
 */
public class Ship extends GameObject {

    private Water water;
    private float lift = 0.1f;

    /**
     * Constructor.
     *
     * @param pos position of the ship
     * @param sprite sprite of the ship
     */
    public Ship(Vector2 pos, String sprite) {
        super(pos, sprite);
    }
    
    @Override
    public void update() {
        float x = super.getPos().x;
        super.setPos(new Vector2(x, water.getHeight(x) + lift));
        super.setAngle(water.getAngle(x) * 0.5f);
    }

    /**
     * Set water on which the ship will sail.
     *
     * @param water Water
     */
    public void setWater(Water water) {
        this.water = water;
    }

}
