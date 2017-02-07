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
public class Vector2 {

    public final float x;
    public final float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(int x, int y) {
        this.x = (float) x;
        this.y = (float) y;
    }

    public Vector2 add(Vector2 pos) {
        return new Vector2(this.x + pos.x, this.y + pos.y);
    }

    public Vector2 subtract(Vector2 pos) {
        return new Vector2(this.x - pos.x, this.y - pos.y);
    }
    
    public Vector2 divide(float x) {
        return new Vector2(this.x / x, this.y / x);
    }
    
    public Vector2 multiply(float x) {
        return new Vector2(this.x * x, this.y * x);
    }

    public boolean isEqualTo(Vector2 pos) {
        return x == pos.x && y == pos.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
