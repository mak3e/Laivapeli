/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import com.mak3e.laivapeli.game.CameraTarget;
import java.awt.Graphics2D;
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
public class CameraTest {
    
    Camera camera;
    
    public CameraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        camera = new Camera(3.125f);
        camera.setTarget(new CameraTarget(new Vector2(1f,1f),0));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void cameraSetView() {
        camera.setView(100, 100);
        assertTrue(camera.getViewSize().isEqualTo(new Vector2(100,100)));
    }
    
    @Test
    public void cameraWorldPointToScreenPointCenter() {
        camera.setView(100, 50);
        Vector2 tv2 = camera.worldPointToScreenPoint(new Vector2(1f,1f));
        assertTrue(tv2.isEqualTo(new Vector2(50f, 25f)));        
    }
    
    @Test
    public void cameraWorldPointToScreenPointOutsideRegion() {
        camera.setView(100, 100);
        Vector2 tv2 = camera.worldPointToScreenPoint(new Vector2(-3f,1f));
        assertTrue(tv2.isEqualTo(new Vector2(-78f, 50f))); //Counted by hand       
    }
    
    @Test
    public void cameraWorldPointToScreenPointVertical() {
        camera.setView(100, 50);
        Vector2 tv2 = camera.worldPointToScreenPoint(new Vector2(1f,1.5f));
        assertTrue(tv2.isEqualTo(new Vector2(50f, 9f))); //Counted by hand       
    }
    
    @Test
    public void cameraGetPixelsPerUnit() {
        camera.setView(200, 150);
        assertEquals(64f, camera.getPixelsPerUnit(), 0f);
    }
    
    @Test
    public void cameraGetUnitsPerHeight() {
        camera.setView(200, 400);
        assertEquals(6.25f, camera.getUnitsPerHeight(), 0f);
    }
    
    public void cameraFPSTest() {
        camera.fpsClock.tick();
        assertTrue(camera.getFps()>0);
    }
}
