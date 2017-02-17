/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * GameObject is a base class which will be extended by the game objects.
 *
 * @author Make
 */
public abstract class GameObject {

    //1 unit is about 10m
    //1 unit is 256 pixels when width is 800 and camera zoom is 1
    //and sprites are rendered 4 times smaller
    private Vector2 pos;
    private float angle = 0f; //Angle
    private Image sprite; //Sprite

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
        this.sprite = load(sprite);
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
            camera.drawSprite(pos, angle, sprite);
        }
    }

    /**
     * Update method used for game object specific functions.
     */
    public abstract void update();

    /**
     * Get position of a game object.
     *
     * @return position as Vector2
     */
    public Vector2 getPos() {
        return pos;
    }

    /**
     * Set position of a game object.
     *
     * @param pos new position
     */
    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    /**
     * Get angle of a game object.
     *
     * @return angle in degrees as float
     */
    public float getAngle() {
        return angle;
    }

    /**
     * Set angle of a game object.
     *
     * @param angle in degrees as float
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }

    /**
     * Load image from resources.
     *
     * @param path file name as string
     * @return image as BufferedImage
     */
    BufferedImage load(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("resources" + File.separator + path));
        } catch (IOException ex) {
            System.out.println("Error while loading a resource");
        }
        return image;

    }
}
