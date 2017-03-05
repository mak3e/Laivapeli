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
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * Smoke.
 *
 * @author Make
 */
public class Smoke extends GameObject {

    private final int maxParticles = 20;
    private final float rate = 0.05f;
    private final float startScale = 0.1f;
    private final float scaleFactor = 0.5f;
    private final Vector2 posFactor = new Vector2(0f, 0.2f);
    private final boolean respawn = true;
    private float time = 0f;
    private final Ship ship;
    private int lastShipDirection = 1;
    private final List<GameObject> particles = new ArrayList();
    private final Random random = new Random();

    /**
     * Constructor.
     *
     * @param ship ship
     */
    public Smoke(Ship ship) {
        super(new Vector2(0f, 0f));
        this.ship = ship;
    }

    @Override
    public void update() {
        if (time > rate) {
            nextParticle();
            time -= rate;
        }
        time += Clock.time.getDeltaTime();
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
        }
        if (ship.getDirection() != lastShipDirection) {
            reset();
            lastShipDirection = ship.getDirection();
        }

    }

    void nextParticle() {
        if (particles.size() > maxParticles) {
            particles.remove(0);
        }
        float deltaTime = Clock.time.getDeltaTime();
        float angle = ship.getAngle() - 16f * ship.getDirection();
        float distance = 0.4f;
        float x = (float) (Math.sin(Math.toRadians(angle)) * distance);
        float y = (float) (Math.cos(Math.toRadians(angle)) * distance);

        Vector2 pos = ship.getPos().add(new Vector2(x, y));
        GameObject particle = new GameObject(pos, "smoke") {
            @Override
            public void update() {
                this.addScale(deltaTime * scaleFactor);
                this.addPos(posFactor.multiply(deltaTime));
            }

            @Override
            public void debug(Camera camera) {
                camera.drawCircleDebug(this.getPos(), this.getScale() * 0.1f);
            }
        };
        particle.setAngle(random.nextFloat() * 360f);
        particle.setScale(startScale + startScale * ship.getAcceleration());
        particles.add(particle);
    }

    void reset() {
        particles.removeAll(particles);
    }

    @Override
    public void capture(Camera camera) {
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).capture(camera);
        }
    }

    @Override
    public void debug(Camera camera) {
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).debug(camera);
        }
    }

}
