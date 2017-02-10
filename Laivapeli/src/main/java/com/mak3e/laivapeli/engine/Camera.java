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
 * Camera is used for drawing the game world on screen
 * 
 * @author Make
 */
public class Camera {
    
    /**
     * Main camera
     */
    public static Camera main = new Camera();
    private GameObject target;
    private Vector2 viewSize;
    private final float unitsPerWidth = 3.125f; // Units per width
    private final Clock fpsClock = new Clock(); // Used for counting fps
    private Graphics2D view;

    public Camera() {
        this.target = null;
    }

    public Camera(GameObject target) {
        this.target = target;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public Vector2 getPos() {
        return target.getPos();
    }

    public Vector2 getViewSize() {
        return viewSize;
    }

    public float getUnitsPerWidth() {
        return unitsPerWidth;
    }

    public float getUnitsPerHeight() {
        return unitsPerWidth / viewSize.x * viewSize.y;
    }

    public void capture(Graphics2D view) {
        if (target != null) {
            this.view = view;
            drawBackground();
            for (GameObject gameObject : Core.engine.getGame().getGameObjects()) {
                gameObject.capture(this);
            }
            drawFps();
            fpsClock.tick();
        }
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

    public void drawSprite(Vector2 pos, float angle, Image sprite) {
        Vector2 size = new Vector2(
                sprite.getWidth(null),
                sprite.getHeight(null)
        ).multiply(this.viewSize.x / 3200);
        Vector2 point = worldPointToScreenPoint(pos);
        view.translate(point.x, point.y); //Translate to image origin
        view.rotate(Math.toRadians(angle)); //Rotate
        view.drawImage(
                sprite,
                (int) -size.x / 2,
                (int) -size.y / 2,
                (int) size.x,
                (int) size.y,
                null
        );
        view.rotate(Math.toRadians(-angle)); //Rotate back
        view.translate(-point.x, -point.y); //Translate back
    }

    public void drawPolygon(Vector2[] points) {
        view.setColor(Color.BLACK);
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            Vector2 screenPoint = this.worldPointToScreenPoint(points[i]);
            xPoints[i] = (int) screenPoint.x;
            yPoints[i] = (int) screenPoint.y;
        }
        view.fillPolygon(xPoints, yPoints, points.length);
    }

    int getFps() {
        return (int) fpsClock.getTicksPerSecond();
    }

    float getPixelsPerUnit() {
        return viewSize.x / getUnitsPerWidth();
    }

    Vector2 worldPointToScreenPoint(Vector2 pos) {
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
