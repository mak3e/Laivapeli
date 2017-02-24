/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import java.awt.Image;

/**
 * GameObject is a base class which will be extended by the game objects.
 *
 * @author Make
 */
public abstract class GameObject {

    private Vector2 pos;
    private float angle = 0f; //Angle
    private float scale = 1f;
    private Image sprite; //Sprite
    private String spriteName;
    private int direction = 1; //Horizontal direction

    /**
     * Constructor.
     *
     * Create new game object
     *
     * @param pos position as Vector2
     */
    public GameObject(Vector2 pos) {
        this.pos = pos;
    }

    /**
     * Constructor.
     *
     * Create new game object with sprite
     *
     * @param pos position as Vector2
     * @param sprite image name as string (images are in resources folder)
     */
    public GameObject(Vector2 pos, String sprite) {
        this(pos);
        this.spriteName = sprite;
        this.sprite = Resources.files.getImage(sprite);
    }

    /**
     * Capture game object.
     *
     * Draw game objects sprite on camera
     *
     * @param camera Camera
     */
    public void capture(Camera camera) {
        if (sprite != null) {
            camera.drawObject(this);
        }
    }

    /**
     * Debug game object.
     *
     * Draw game object info on camera
     *
     * @param camera Camera
     */
    public void debug(Camera camera) {
        if (sprite != null) {
            camera.drawObjectDebug(this);
        }
    }

    /**
     * Update game object.
     */
    public abstract void update();

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public void addPos(Vector2 pos) {
        this.pos = this.pos.add(pos);
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void addAngle(float angle) {
        this.angle += angle;
    }

    public float getAngle() {
        return angle;
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }

    public int getDirection() {
        return direction;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void addScale(float scale) {
        this.scale += scale;
    }

    public float getScale() {
        return scale;
    }

    public Image getSprite() {
        return sprite;
    }

    public String getSpriteName() {
        return spriteName;
    }
}
