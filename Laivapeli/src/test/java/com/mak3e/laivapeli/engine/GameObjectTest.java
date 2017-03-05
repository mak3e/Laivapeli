/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import java.awt.Image;
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
public class GameObjectTest {

    public GameObjectTest() {
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
    public void GameObjectTest() {
        GameObject go = new GameObject(new Vector2(0, 0)) {
            @Override
            public void update() {

            }
        };
    }

    @Test
    public void GameObjectAddAngleTest() {
        GameObject go = new GameObject(new Vector2(0, 0)) {
            @Override
            public void update() {

            }
        };
        go.addAngle(90f);
        go.addAngle(10f);
        assertEquals(100f, go.getAngle(), 0f);
    }

    @Test
    public void GameObjectDirectionScaleTest() {
        GameObject go = new GameObject(new Vector2(0, 0)) {
            @Override
            public void update() {

            }
        };
        assertEquals(1, go.getDirection());
        assertEquals(1f, go.getScale(), 0f);
        go.setDirection(-1);
        go.addScale(1f);
        assertEquals(-1, go.getDirection());
        assertEquals(2f, go.getScale(), 0f);
    }

    @Test
    public void GameObjectGetSpriteTest() {
        GameObject go = new GameObject(new Vector2(0, 0), "ship") {
            @Override
            public void update() {

            }
        };
        assertTrue(go.getSprite() != null);
    }

}
