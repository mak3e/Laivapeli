/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

/**
 * Vector2 is used for storing and counting with 2 dimensional float values.
 *
 * @author Make
 */
public class Vector2 {

    public final float x;
    public final float y;

    /**
     * Constructor.
     *
     * Set values of Vector2
     *
     * @param x x value as float
     * @param y y value as float
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Add Vector2 to Vector2.
     *
     * @param pos Vector2
     * @return new Vector2
     */
    public Vector2 add(Vector2 pos) {
        return new Vector2(this.x + pos.x, this.y + pos.y);
    }

    /**
     * Subtract Vector2 from Vector2.
     *
     * @param pos Vector2 to subtract
     * @return new Vector2
     */
    public Vector2 subtract(Vector2 pos) {
        return new Vector2(this.x - pos.x, this.y - pos.y);
    }

    /**
     * Divide Vector2 with value x.
     *
     * @param x divider as float
     * @return Vector2
     */
    public Vector2 divide(float x) {
        return new Vector2(this.x / x, this.y / x);
    }

    /**
     * Multiply Vector2 with value x.
     *
     * @param x multiplier
     * @return Vector2
     */
    public Vector2 multiply(float x) {
        return new Vector2(this.x * x, this.y * x);
    }

    /**
     * Check if 2 vectors are equal.
     *
     * @param pos Vector2
     * @return true if equal false if not
     */
    public boolean isEqualTo(Vector2 pos) {
        return x == pos.x && y == pos.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
