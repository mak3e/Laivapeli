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
    public void WaterGetHeightTest() {
        Water water = new Water(new Vector2(0,0), 10);
        assertEquals(21f, water.heightBetween(24f, 12f, 0.75f), 0f);
    }
}
