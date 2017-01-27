/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

/**
 *
 * @author Make
 */
public class Clock {
    
    private float deltaTime = 0;
    private long oldTime = 0;
    private long newTime = 0;
    
    public void tick() {
        delta(System.nanoTime());
    }
    
    void delta(long time) {
        newTime = time;
        deltaTime = ((newTime - oldTime) / 1000000000f);
        oldTime = newTime;
    }
    
    public float getDeltaTime() {
        return deltaTime;
    }
    
}
