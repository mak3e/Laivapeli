/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.mak3e.laivapeli.game.Game;

/**
 *
 * @author Make
 */
public class CoreTest {

    public CoreTest() {
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
    public void coreTest() {
        assertTrue(Core.engine != null);
        assertTrue(Resources.files.getStatus());
    }

    @Test
    public void coreGameTest() {
        assertTrue(Core.engine.getGame() != null);
    }

    public void coreUpdateTest() {
        Core.engine.update();
        assertTrue(Clock.time.getDeltaTime() != 0);
    }
}
