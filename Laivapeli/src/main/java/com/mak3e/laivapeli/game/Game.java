/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.Camera;
import com.mak3e.laivapeli.engine.GameObject;
import com.mak3e.laivapeli.engine.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 * Game class constructs the game world
 *
 * @author Make
 */
public class Game {

    private List<GameObject> game = new ArrayList<>();
    private float levelWidth = 100f;

    /**
     * Initialize game
     *
     * Add all objects to game world
     */
    public Game() {
        // Create objects
        CameraTarget ct = new CameraTarget(new Vector2(1f, 0f), levelWidth);
        Ship ship = new Ship(new Vector2(1f, 0f), "ship.png");
        Water water = new Water(new Vector2(0f, 0f), levelWidth);

        // Initialize objects
        ct.setCamera(Camera.main);
        ct.followTarget(ship);
        ship.setWater(water);

        // Add objects to the game
        game.add(ct);
        for (int i = 0; i < 4; i++) {
            game.add(new Box(new Vector2(
                    1f, 1f - (i / 4f)), new String[]{"box1.png"})
            );
        }
        game.add(ship);
        game.add(water);
    }

    /**
     * Return all GameObjects of the game world
     *
     * @return a list of GameObjects
     */
    public List<GameObject> getGameObjects() {
        return game;
    }
}
