/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Make
 */
public class GameObject {

    //1 unit is about 10m
    //1 unit is 256 pixels when width is 800 and camera zoom is 1
    //and sprites are rendered 4 times smaller
    private Vector2 pos;
    private int layer = 0;
    private float angle = 0f; //Angle
    private Image sprite; //Sprite

    public GameObject(Vector2 pos, int layer) {
        this.pos = pos;
        this.layer = layer;
    }

    public GameObject(Vector2 pos, int layer, Image sprite) {
        this(pos, layer);
        this.sprite = sprite;
    }

    public void capture(Camera camera) {
        if (sprite != null && camera.getLayer() >= this.layer) {
            camera.drawSprite(pos, angle, sprite);
        }
    }

    public void update() {
        // Subclasses will use this method for updating
    }

    public Vector2 getPos() {
        return pos;
    }

    public int getLayer() {
        return layer;
    }

    public float getA() {
        return angle;
    }
}
