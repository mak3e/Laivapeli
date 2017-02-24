/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import com.mak3e.laivapeli.game.Game;

/**
 * Core takes care of the game engine.
 *
 * @author Make
 */
public class Core {

    public static Core engine = new Core();
    public static boolean debug = false;
    private Game game = new Game();

    /**
     * Constructor.
     *
     * Initialize core
     */
    public Core() {
        Resources.files.load();
        game = new Game();
    }

    /**
     * Get Game (contains all game objects in the game).
     *
     * @return Game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Get game clock.
     *
     * @return Clock
     */
    /**
     * Update game objects and make clock tick.
     */
    public void update() {
        for (GameObject gameobject : game.getGameObjects()) {
            gameobject.update();
        }
        Clock.time.tick();
    }

}
