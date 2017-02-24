/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.Clock;
import com.mak3e.laivapeli.engine.GameObject;
import com.mak3e.laivapeli.engine.Input;
import com.mak3e.laivapeli.engine.Sound;
import com.mak3e.laivapeli.engine.Vector2;

/**
 * Ship. Floats on water
 *
 * @author Make
 */
public class Ship extends GameObject {

    private Water water;
    private float lift = 0.1f;
    private float maxSpeed = 0.4f;
    private float friction = 1f;
    private float acceleration = 0f;
    private float velocity = 0f;

    /**
     * Constructor.
     *
     * @param pos position of the ship
     */
    public Ship(Vector2 pos) {
        super(pos, "ship");
        Sound.audio.play("idleship", true);
        //super.setDirection(-1);
    }

    @Override
    public void update() {
        float x = super.getPos().x;
        float deltaTime = Clock.time.getDeltaTime();
        acceleration = Input.in.getAxis().x;
        velocity += (acceleration - velocity * friction) * deltaTime;
        velocity = Math.min(maxSpeed, velocity);
        velocity = Math.max(-maxSpeed, velocity);
        super.setPos(new Vector2(x, water.getHeight(x) + lift));
        super.addPos(new Vector2(velocity * deltaTime, 0f));
        super.setAngle(water.getAngle(x) * 0.5f - velocity * 5f);
        if (acceleration != 0f) {
            super.setDirection((int) Math.ceil(acceleration));
            Sound.audio.play("ship", true);
        } else {
            Sound.audio.stop("ship");
        }
    }

    /**
     * Get acceleration of the ship.
     *
     * @return acceleration
     */
    public float getAcceleration() {
        return Math.abs(acceleration);
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
