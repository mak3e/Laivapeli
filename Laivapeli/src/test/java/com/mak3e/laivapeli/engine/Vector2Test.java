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
public class Vector2Test {

    public Vector2Test() {
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
    public void vector2IsEqualTo() {
        Vector2 tv2 = new Vector2(1.23f, 3.45f);
        Vector2 tv2_2 = new Vector2(1.23f, 3.45f);
        assertTrue(tv2.isEqualTo(tv2_2));
    }

    @Test
    public void vector2Add() {
        Vector2 tv2 = new Vector2(1.0f, 2.0f);
        tv2 = tv2.add(new Vector2(2.0f, 1.0f));
        assertTrue(tv2.isEqualTo(new Vector2(3.0f, 3.0f)));
    }

    @Test
    public void vector2Add2() {
        Vector2 tv2 = new Vector2(-1.0f, -5.0f);
        tv2 = tv2.add(new Vector2(-2.0f, 6.0f));
        assertTrue(tv2.isEqualTo(new Vector2(-3.0f, 1.0f)));
    }

    @Test
    public void vector2Subtract() {
        Vector2 tv2 = new Vector2(1.0f, 2.0f);
        tv2 = tv2.subtract(new Vector2(2.0f, 1.0f));
        assertTrue(tv2.isEqualTo(new Vector2(-1.0f, 1.0f)));
    }

    @Test
    public void vector2Subtract2() {
        Vector2 tv2 = new Vector2(-5.0f, -2.0f);
        tv2 = tv2.subtract(new Vector2(-2.0f, 5.0f));
        assertTrue(tv2.isEqualTo(new Vector2(-3.0f, -7.0f)));
    }

    @Test
    public void vector2Divide() {
        Vector2 tv2 = new Vector2(-5.0f, -2.0f);
        tv2 = tv2.divide(2f);
        assertTrue(tv2.isEqualTo(new Vector2(-2.5f, -1.0f)));
    }

    @Test
    public void vector2Multiply() {
        Vector2 tv2 = new Vector2(-5.0f, -2.0f);
        tv2 = tv2.multiply(2f);
        assertTrue(tv2.isEqualTo(new Vector2(-10.0f, -4.0f)));
    }

    @Test
    public void vector2ToString() {
        assertEquals(new Vector2(1f, 1f).toString(), "(1.0, 1.0)");
    }
}
