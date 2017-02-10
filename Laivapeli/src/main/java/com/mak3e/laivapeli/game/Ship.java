/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.GameObject;
import com.mak3e.laivapeli.engine.Vector2;

/**
 * A ship which floats on water
 * 
 * @author Make
 */
public class Ship extends GameObject {

    private Water water;

    public Ship(Vector2 pos, String sprite) {
        super(pos, sprite);
    }

    //ACTION LISTENER WILL CALL SOMETHING HERE
    @Override
    public void update() {
    }

    public void setWater(Water water) {
        this.water = water;
    }

}
