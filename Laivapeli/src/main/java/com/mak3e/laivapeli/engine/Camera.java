/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author Make
 */
public class Camera extends GameObject {

    public static Camera main = new Camera(new Vector2(1, 0), 3);

    private Vector2 viewSize;
    private final float unitsPerWidth = 3.125f; // Units per width
    private Clock fpsClock = new Clock(); // Used for counting fps
    private Graphics2D view;

    public Camera(Vector2 pos, int layer) {
        super(pos, layer);
    }

    public Vector2 getViewSize() {
        return viewSize;
    }

    public float getUnitsPerWidth() {
        return unitsPerWidth;
    }

    @Override
    public void update() {

    }

    public void capture(Graphics2D view) {
        this.view = view;
        drawBackground();
        for (GameObject gameObject : Core.engine.getGameObjects()) {
            gameObject.capture(this);
        }
        drawFps();
        fpsClock.tick();
    }

    void drawBackground() {
        view.setColor(Color.WHITE);
        view.fillRect(0, 0, (int) viewSize.x, (int) viewSize.y);
    }

    void drawFps() {
        view.setColor(Color.BLACK);
        String fpsText = "FPS: " + String.valueOf(getFps());
        view.drawString(fpsText, 10, 10);
    }

    void drawSprite(Vector2 pos, float angle, Image sprite) {

        //view.drawImage(sprite, xx, yy, width, height , null);
    }

    int getFps() {
        return (int) (1f / fpsClock.getDeltaTime());
    }

    float getPixelsPerUnit() {
        return viewSize.x / getUnitsPerWidth();
    }

    public Vector2 worldPointToScreenPoint(Vector2 pos) {
        Vector2 result = pos.subtract(this.getPos());
        float ppu = getPixelsPerUnit();
        int x = (int) ((result.x * ppu) + (viewSize.x / 2));
        int y = (int) ((-result.y * ppu) + (viewSize.y / 2));
        return new Vector2(x, y);
    }

    public void setView(int width, int height) {
        viewSize = new Vector2(width, height);
    }

}
