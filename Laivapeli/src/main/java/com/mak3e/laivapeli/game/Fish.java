/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.Camera;
import com.mak3e.laivapeli.engine.Clock;
import com.mak3e.laivapeli.engine.GameObject;
import com.mak3e.laivapeli.engine.Sound;
import com.mak3e.laivapeli.engine.Vector2;
import java.util.Random;

/**
 * Fish lives in Water.
 *
 * @author Make
 */
public class Fish extends GameObject {

    private Ship ship;
    private Water water;
    private float spinTimer = 0f;
    private float spinLength = 1f;
    private float lift = -0.01f;
    private boolean enabled = true;
    private Random random = new Random();

    /**
     * Constructor.
     *
     * @param water water
     * @param ship ship
     */
    public Fish(Water water, Ship ship) {
        this(water, ship, "fish");
    }

    /**
     * Constructor.
     *
     * @param water water
     * @param ship ship
     * @param bg background?
     */
    public Fish(Water water, Ship ship, boolean bg) {
        this(water, ship, "gfish");
        super.setScale(0.75f);
    }

    /**
     * Constructor.
     *
     * @param water water
     * @param ship ship
     * @param sprite sprite of fish
     */
    Fish(Water water, Ship ship, String sprite) {
        super(new Vector2(0f, 0f), sprite);
        this.water = water;
        this.ship = ship;
        reset();
    }

    @Override
    public void update() {
        float deltaTime = Clock.time.getDeltaTime();
        if (spinTimer > spinLength) {
            reset();
            spinTimer -= spinLength;
        }
        float x = super.getPos().x;
        super.setPos(new Vector2(x, water.getHeight(x) + lift));
        if (enabled) {
            super.addAngle(super.getDirection() * deltaTime / spinLength * 360);
        }
        spinTimer += deltaTime;
    }

    /**
     * Reset fish.
     *
     * Moves fish to a new location and makes it jump with 1/10 chance
     */
    public void reset() {
        float spawn = Camera.main.getUnitsPerWidth();
        super.setAngle(180f);
        if (random.nextBoolean()) {
            super.setDirection(super.getDirection() * -1);
        }
        enabled = (random.nextInt(10) == 0);
        if (enabled) {
            Sound.audio.play("fish", false);
        }
        float x = ship.getPos().x + (random.nextFloat() * spawn - spawn / 2) * 1.5f;
        super.setPos(new Vector2(x, 0));
        spinLength = 1f + 0.2f * random.nextFloat();
    }

}
