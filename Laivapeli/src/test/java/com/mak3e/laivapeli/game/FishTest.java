/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.Vector2;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Make
 */
public class FishTest {

    public FishTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void fishResetTest() {
        Water water = new Water(new Vector2(0f, 0f), 100f);
        Ship ship = new Ship(new Vector2(1f, 0f));
        Fish fish = new Fish(water, ship, true);
        fish.reset();
        assertEquals(180f, fish.getAngle(), 0f);
        assertEquals(0.75f, fish.getScale(), 0f);
        fish.update();

    }
}
