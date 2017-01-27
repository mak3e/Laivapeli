/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Make
 */
public class Core {
    
    public static Core engine = new Core();
    private ArrayList<GameObject> gameobjects = new ArrayList();
    private ArrayList<PhysicsObject> physicsobjects = new ArrayList();
    private ArrayList<Box> boxes = new ArrayList();
    private Clock clock = new Clock(); // Used for constant speeds
    private Ship ship;
    
    public Core() {
        System.out.println("Laivapeli is running!");
        
        //Initialization
        ship = new Ship(new Vector2(1f, 0f), 1, load("ship.png")); //Init ship
        physicsobjects.add(ship);
        physicsobjects.addAll(boxes);
        gameobjects.addAll(physicsobjects);
        
    }
    
    public ArrayList<GameObject> getGameObjects() {
        return gameobjects;
    }
    
    public void update() {
        for (GameObject gameobject: gameobjects) {
            gameobject.update();
        }
        clock.tick();
    }
    
    BufferedImage load(String path) {
        BufferedImage image = null;
        try {                
            image = ImageIO.read(new File("resources" + File.separator + path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return image;
        
       
    }
    
}
