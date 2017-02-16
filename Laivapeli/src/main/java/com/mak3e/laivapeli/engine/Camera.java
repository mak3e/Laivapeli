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

    public static Camera main = new Camera(3.125f); // Main camera
    private GameObject target; // Target's position is camera's position
    private Vector2 viewSize; // Size of UI in pixels
    private float unitsPerWidth = 3.125f; // Units per width
    private final Clock fpsClock = new Clock(); // Used for counting fps
    private Graphics2D view; // Holds each frame until they're drawn

    /**
     * Constructor
     *
     * @param unitsPerWidth (required) amount of game world units displayed per
     * window width
     */
    public Camera(float unitsPerWidth) {
        this.unitsPerWidth = unitsPerWidth;
    }

    /**
     * Set target for camera to follow
     *
     * @param target GameObject
     */
    public void setTarget(GameObject target) {
        this.target = target;
    }

    /**
     * Get camera's position
     *
     * @return camera's position
     */
    public Vector2 getPos() {
        return target.getPos();
    }

    /**
     * Get view size in pixels
     *
     * @return view size as Vector2
     */
    public Vector2 getViewSize() {
        return viewSize;
    }

    /**
     * Get units per window width
     *
     * @return units per width
     */
    public float getUnitsPerWidth() {
        return unitsPerWidth;
    }

    /**
     * Get units per window height
     *
     * @return units per height
     */
    public float getUnitsPerHeight() {
        return unitsPerWidth / viewSize.x * viewSize.y;
    }

    /**
     * Capture the game world
     *
     * Captures the game world by asking game objects to draw on view
     *
     * @param view Graphics2D object passed from UI's paintComponent method
     */
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

    /**
     * Draw background of game world
     */
    void drawBackground() {
        view.setColor(Color.WHITE);
        view.fillRect(0, 0, (int) viewSize.x, (int) viewSize.y);
    }

    /**
     * Draw FPS to the top left corner
     */
    void drawFps() {
        view.setColor(Color.BLACK);
        String fpsText = "FPS: " + String.valueOf(getFps());
        view.drawString(fpsText, 10, 10);
    }

    /**
     * Draw sprite
     *
     * Method called by game objects when captured
     *
     * @param pos game world position of the object
     * @param angle game world angle of the object
     * @param sprite sprite of the object
     */
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

    /**
     * Draw polygon
     *
     * Used for drawing water
     *
     * @param points game world points of the polygon
     */
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

    /**
     * Get FPS
     *
     * @return FPS as integer
     */
    int getFps() {
        return (int) fpsClock.getTicksPerSecond();
    }

    /**
     * Get pixels per unit
     *
     * @return pixels per unit
     */
    float getPixelsPerUnit() {
        return viewSize.x / getUnitsPerWidth();
    }

    /**
     * World points to screen points
     *
     * @param pos world point as Vector2
     * @return screen point as Vector2
     */
    Vector2 worldPointToScreenPoint(Vector2 pos) {
        Vector2 result = pos.subtract(this.getPos());
        float ppu = getPixelsPerUnit();
        int x = (int) ((result.x * ppu) + (viewSize.x / 2));
        int y = (int) ((-result.y * ppu) + (viewSize.y / 2));
        return new Vector2(x, y);
    }

    /**
     * Set view size
     *
     * @param width window width
     * @param height window height
     */
    public void setView(int width, int height) {
        viewSize = new Vector2(width, height);
    }

}
