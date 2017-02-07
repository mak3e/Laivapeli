/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.PhysicsObject;
import com.mak3e.laivapeli.engine.Vector2;
import java.util.Random;

/**
 *
 * @author Make
 */
public class Box extends PhysicsObject {

    public Box(Vector2 pos, int layer, String[] sprites) {
        super(pos, layer, sprites[new Random().nextInt(sprites.length)]);
    }
    
}
