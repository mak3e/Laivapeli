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
public class WaterTest {
    
    public WaterTest() {
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
    public void waterHeightBetweenTest() {
        Water water = new Water(new Vector2(0,0), 10);
        assertEquals(21f, water.heightBetween(24f, 12f, 0.75f), 0f);
    }
    
    @Test
    public void waterGetHeightTest() {
        Water water = new Water(new Vector2(0,0), 10);
        water.getHeight(5f);
    }
    
    @Test
    public void waterAngleOfTest() {
        Water water = new Water(new Vector2(0,0), 10);
        assertEquals(45f, water.angleOf(5f, 5f), 0f);
    }
    
    @Test
    public void waterGetAngleTest() {
        Water water = new Water(new Vector2(0,0), 10);
        water.getAngle(5f);
    }
    
    @Test
    public void waterUpdatesTest() {
        Water water = new Water(new Vector2(0,0), 10);
        water.update();
    }
    
    @Test
    public void waterInRangeTest() {
        Water water = new Water(new Vector2(0,0), 10);
        assertTrue(water.inRange(5f));
        assertTrue(!water.inRange(15f));
    }
    
    @Test
    public void waterGetClosestSpringHeightsTest() {
        Water water = new Water(new Vector2(0,0), 10);
        assertTrue(
                new Vector2(0,0).isEqualTo(water.getClosestSpringHeights(-1f)));
    }
}
