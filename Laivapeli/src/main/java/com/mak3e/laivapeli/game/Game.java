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
 *
 * @author Make
 */
public class Game {
    
    private List<GameObject> game = new ArrayList<>();
    
    public Game() {
        game.add(new Ship(new Vector2(1f, 0f), 1, "ship.png"));
        for (int i = 0; i < 4; i++) {
            game.add(new Box(new Vector2(
                    1f, 1f - (i / 4f)), 0, new String[]{"box1.png"})
            );
        }
        
    }
    
    public List<GameObject> getGameObjects() {
        return game;
    }
}
