/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

/**
 * Input is used for passing input from UI to game objects.
 *
 * @author Make
 */
public class Input {

    public static Input in = new Input();
    private Vector2 axis;

    /**
     * Get axis.
     *
     * @return axis as vector2
     */
    public Vector2 getAxis() {
        return axis;
    }

    /**
     * Set input axis.
     *
     * x and y values larger than 1 will changed to 1
     *
     * @param axis input axis
     */
    public void setAxis(Vector2 axis) {
        float x = axis.x;
        float y = axis.y;
        x = Math.min(1f, x);
        x = Math.max(-1f, x);
        y = Math.min(1f, y);
        y = Math.max(-1f, y);
        this.axis = new Vector2(x, y);
    }
}
