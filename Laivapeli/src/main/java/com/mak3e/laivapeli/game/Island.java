/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.GameObject;
import com.mak3e.laivapeli.engine.Vector2;

/**
 * Island.
 *
 * @author Make
 */
public class Island extends GameObject {

    /**
     * Constructor.
     *
     * @param pos position
     */
    public Island(Vector2 pos) {
        super(pos, "island");
    }

    @Override
    public void update() {

    }

}
