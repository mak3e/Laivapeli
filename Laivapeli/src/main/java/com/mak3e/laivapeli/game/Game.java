/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.Camera;
import com.mak3e.laivapeli.engine.GameObject;
import com.mak3e.laivapeli.engine.Sound;
import com.mak3e.laivapeli.engine.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 * Game class constructs the game world.
 *
 * @author Make
 */
public class Game {

    private final List<GameObject> game = new ArrayList<>();
    private final float levelWidth = 100f;

    /**
     * Initialize game.
     *
     * Add all objects to game world
     */
    public Game() {        
        // Create objects
        CameraTarget ct = new CameraTarget(new Vector2(1f, 0f), levelWidth);
        Ship ship = new Ship(new Vector2(levelWidth * 0.5f, 0f));
        Smoke smoke = new Smoke(ship);
        Water water = new Water(new Vector2(0f, 0f), levelWidth);
        Water bgWater = new Water(new Vector2(0f, 0f), levelWidth, true, 0.5f);
        Harbor harbor = new Harbor(new Vector2(0.1f, 0.1f));
        Harbor harbor2 = new Harbor(new Vector2(levelWidth - 0.1f, 0.1f));
        Island island = new Island(new Vector2(levelWidth * 0.5f, 0.25f));
        Island island2 = new Island(new Vector2(levelWidth * 0.5f - 1f, 0.15f));
        Island island3 = new Island(new Vector2(levelWidth * 0.5f + 1f, 0.15f));
        Island island4 = new Island(new Vector2(levelWidth * 0.25f, 0.15f));
        Island island5 = new Island(new Vector2(levelWidth * 0.75f, 0.15f));
        harbor2.setDirection(-1);
        List<Fish> fishes = new ArrayList();
        List<Fish> bgFishes = new ArrayList();
        for (int i = 0; i < 3; i++) {
            Fish fish = new Fish(water, ship);
            fishes.add(fish);
            Fish bgFish = new Fish(bgWater, ship, true);
            bgFishes.add(bgFish);
        }

        // Initialize objects
        ct.setCamera(Camera.main);
        ct.followTarget(ship);
        ship.setWater(water);

        // Add objects to the game
        game.add(ct);
        game.addAll(bgFishes);
        game.add(bgWater);
        game.add(harbor);
        game.add(harbor2);
        game.add(island);
        game.add(island2);
        game.add(island3);
        game.add(island4);
        game.add(island5);
        game.add(smoke);
        game.add(ship);
        game.addAll(fishes);
        game.add(water);
        
        // Add ocean background sound
        Sound.audio.play("ocean", true);
    }

    /**
     * Return all GameObjects of the game world.
     *
     * @return a list of GameObjects
     */
    public List<GameObject> getGameObjects() {
        return game;
    }
}
