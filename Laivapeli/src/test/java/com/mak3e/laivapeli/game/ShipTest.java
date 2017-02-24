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
public class ShipTest {

    public ShipTest() {
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
    public void shipFloatsOnWaterTest() {
        Ship ship = new Ship(new Vector2(1f, 0f));
        Water water = new Water(new Vector2(0f, 0f), 10f);
        ship.setWater(water);
        ship.update();
        assertTrue(new Vector2(1f, 0.1f).isEqualTo(ship.getPos()));
        assertEquals(0f, ship.getAngle(), 0f);

    }
}
