/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

/**
 * Clock is used for timing.
 *
 * @author Make
 */
public class Clock {

    private float deltaTime = 0;
    private long oldTime = System.nanoTime();
    private long newTime = 0;

    /**
     * Tick tock tick tock.
     */
    public void tick() {
        delta(System.nanoTime());
    }

    /**
     * Count delta time between ticks.
     *
     * @param time nano time
     */
    void delta(long time) {
        newTime = time;
        deltaTime = ((newTime - oldTime) / 1000000000f);
        oldTime = newTime;
    }

    /**
     * Get ticks per second.
     *
     * @return ticks per second as float
     */
    public float getTicksPerSecond() {
        return 1f / deltaTime;
    }

    /**
     * Get delta time between ticks.
     *
     * @return delta time in seconds as float
     */
    public float getDeltaTime() {
        return deltaTime;
    }

}
