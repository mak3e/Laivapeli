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
 * GameObject is a base class which will be extended by the game objects
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

    public GameObject(Vector2 pos) {
        this.pos = pos;
    }

    public GameObject(Vector2 pos, String sprite) {
        this(pos);
        this.sprite = load(sprite);
    }

    public void capture(Camera camera) {
        if (sprite != null) {
            camera.drawSprite(pos, angle, sprite);
        }
    }

    public abstract void update();
    
    public Vector2 getPos() {
        return pos;
    }
    
    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public float getA() {
        return angle;
    }
    
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
