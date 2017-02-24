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

/**
 *
 * @author Make
 */
public class InputTest {

    public InputTest() {
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
    public void inputSetAxisTestLargerThan() {
        Input in = new Input();
        in.setAxis(new Vector2(10f, 10f));
        System.out.println(in.getAxis());
        assertTrue(new Vector2(1f, 1f).isEqualTo(in.getAxis()));
    }

    @Test
    public void inputSetAxisTestLargerAndSmallerThan() {
        Input in = new Input();
        in.setAxis(new Vector2(-10f, 10f));
        assertTrue(new Vector2(-1f, 1f).isEqualTo(in.getAxis()));
    }

    @Test
    public void inputSetAxisTestSmallerThan() {
        Input in = new Input();
        in.setAxis(new Vector2(-10f, -10f));
        assertTrue(new Vector2(-1f, -1f).isEqualTo(in.getAxis()));
    }

    @Test
    public void inputSetAxisTestInRange() {
        Input in = new Input();
        in.setAxis(new Vector2(-0.1f, 0.1f));
        assertTrue(new Vector2(-0.1f, +0.1f).isEqualTo(in.getAxis()));
    }
}
