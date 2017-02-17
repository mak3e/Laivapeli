/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.GameObject;
import com.mak3e.laivapeli.engine.Vector2;
import java.util.Random;

/**
 * A box, shipped by a ship.
 *
 * @author Make
 */
public class Box extends GameObject {

    /**
     * Constructor.
     * 
     * @param pos position
     * @param sprites box sprites
     */
    public Box(Vector2 pos, String[] sprites) {
        super(pos, sprites[new Random().nextInt(sprites.length)]);
    }

    @Override
    public void update() {

    }

}
