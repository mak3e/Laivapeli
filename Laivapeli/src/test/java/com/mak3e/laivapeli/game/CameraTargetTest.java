/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.game;

import com.mak3e.laivapeli.engine.Camera;
import com.mak3e.laivapeli.engine.GameObject;
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
public class CameraTargetTest {
    
    public CameraTargetTest() {
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
    public void nextPosInRangeTest() {
        CameraTarget ct = new CameraTarget(new Vector2(0, 0), 100);
        assertTrue(new Vector2(10f, 0f).isEqualTo(
                ct.nextPos(new Vector2(10f, 0f), 10)));      
    }
    
    @Test
    public void nextPosOutRange1Test() {
        CameraTarget ct = new CameraTarget(new Vector2(0, 0), 100);
        assertTrue(new Vector2(5f, 5f).isEqualTo(
                ct.nextPos(new Vector2(0f, 5f), 10)));      
    }
    
    @Test
    public void nextPosOutRange2Test() {
        CameraTarget ct = new CameraTarget(new Vector2(0, 0), 100);
        assertTrue(new Vector2(95f, -5f).isEqualTo(
                ct.nextPos(new Vector2(100f, -5f), 10)));      
    }
    
    @Test
    public void targetTest() {
        CameraTarget ct = new CameraTarget(new Vector2(0, 0), 100);
        Ship ship = new Ship(new Vector2(5f, 5f));
        Camera cam = new Camera(3.125f);
        ct.setCamera(cam);
        ct.followTarget(ship);
        ct.update();
        assertTrue(new Vector2(5f, 5f).isEqualTo(ct.getPos()));
    }
    
    @Test
    public void cameraTest() {
        CameraTarget ct = new CameraTarget(new Vector2(0, 0), 100);
        Camera cam = new Camera(3.125f);
        ct.setCamera(cam);
        assertTrue(cam.getPos().isEqualTo(ct.getPos()));
    }
    
    
}
