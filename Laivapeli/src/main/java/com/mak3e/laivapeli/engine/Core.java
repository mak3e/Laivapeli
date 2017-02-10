/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import com.mak3e.laivapeli.game.Game;

/**
 * Core takes care of the game engine
 * 
 * @author Make
 */
public class Core {
    
    public static Core engine = new Core();
    private final Clock clock = new Clock(); // Used for constant speeds
    private Game game = new Game();
    
    public Core() {
        game = new Game();
    }
    
    public Game getGame() {
        return game;
    }
    
    public void update() {
        for (GameObject gameobject: game.getGameObjects()) {
            gameobject.update();
        }
        clock.tick();
    }
    
}
