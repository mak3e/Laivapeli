/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Camera is used for drawing the game world on screen.
 *
 * @author Make
 */
public class Camera {

    //1 unit is about 10m
    //1 unit is 256 pixels when width is 800 and camera zoom is 1
    //sprites are rendered about 4 times smaller depending of the scale
    public static Camera main = new Camera(3.125f); // Main camera
    public static boolean fps = false; // Show fps?
    private GameObject target; // Target's position is camera's position
    private Vector2 viewSize; // Size of UI in pixels
    private float unitsPerWidth = 3.125f; // Units per width
    public final Clock fpsClock = new Clock(); // Used for counting fps
    private Graphics2D view; // Holds each frame until they're drawn

    /**
     * Constructor.
     *
     * @param unitsPerWidth (required) amount of game world units displayed per
     * window width.
     */
    public Camera(float unitsPerWidth) {
        this.unitsPerWidth = unitsPerWidth;
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

    /**
     * Capture the game world.
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
            if (Camera.fps && this.equals(Camera.main)) {
                drawFps();
            }
            fpsClock.tick();
        }
    }

    /**
     * Debug the game world.
     *
     * Shows debug data of the game world
     *
     * @param view Graphics2D object passed from UI's paintComponent method
     */
    public void debug(Graphics2D view) {
        view.setColor(Color.RED);
        drawDebug();
        if (target != null) {
            this.view = view;
            for (GameObject gameObject : Core.engine.getGame().getGameObjects()) {
                gameObject.debug(this);
            }
        }
    }

    /**
     * Draw background of game world.
     */
    void drawBackground() {
        view.setColor(Color.WHITE);
        view.fillRect(0, 0, (int) viewSize.x, (int) viewSize.y);
    }

    /**
     * Draw FPS to the top left corner.
     */
    void drawFps() {
        view.setColor(Color.BLACK);
        String fpsText = "FPS: " + String.valueOf(getFps());
        view.drawString(fpsText, 10, 10);
    }

    void drawDebug() {
        view.drawString("DEBUG", 10, 25);
    }

    /**
     * Draw sprite.
     *
     * Method called by game objects when captured
     *
     * @param obj game object containing the sprite
     */
    public void drawObject(GameObject obj) {
        Vector2 size = new Vector2(
                obj.getSprite().getWidth(null),
                obj.getSprite().getHeight(null)
        ).multiply(obj.getScale()).multiply(this.viewSize.x / 3200);
        Vector2 point = worldPointToScreenPoint(obj.getPos());
        view.translate(point.x, point.y); //Translate to image origin
        view.rotate(Math.toRadians(obj.getAngle())); //Rotate
        view.drawImage(
                obj.getSprite(),
                (int) -size.x / 2 * obj.getDirection(),
                (int) -size.y / 2,
                (int) size.x * obj.getDirection(),
                (int) size.y,
                null
        );
        view.rotate(Math.toRadians(-obj.getAngle())); //Rotate back
        view.translate(-point.x, -point.y); //Translate back
    }

    /**
     * Sprite draw debug.
     *
     * Method called by game objects when debugging
     *
     * @param obj game object containing the sprite
     */
    public void drawObjectDebug(GameObject obj) {
        Vector2 size = new Vector2(
                obj.getSprite().getWidth(null),
                obj.getSprite().getHeight(null)
        ).multiply(obj.getScale()).multiply(viewSize.x / 3200);
        Vector2 point = worldPointToScreenPoint(obj.getPos());
        view.translate(point.x, point.y);
        String[] debugString = new String[]{
            obj.getSpriteName(),
            "pos:" + obj.getPos().toString(),
            "dir:" + String.valueOf(obj.getDirection()),
            "ang:" + String.valueOf(obj.getAngle())
        };
        for (int i = 0; i < debugString.length; i++) {
            view.drawString(debugString[i], 0, i * 15);
        }
        view.rotate(Math.toRadians(obj.getAngle()));
        view.drawRect(
                (int) (-size.x * 0.5f),
                (int) (-size.y * 0.5f),
                (int) size.x,
                (int) size.y
        );
        view.rotate(Math.toRadians(-obj.getAngle()));
        view.translate(-point.x, -point.y);
    }

    /**
     * Draw circle.
     *
     * Used for drawing a circle
     *
     * @param pos circle position in world points
     * @param radius radius of the circle in world units
     */
    public void drawCircle(Vector2 pos, float radius) {
        Vector2 point = worldPointToScreenPoint(pos);
        int diameter = (int) (radius * 2f * getPixelsPerUnit());
        view.setColor(Color.BLACK);
        view.fillOval(
                (int) (point.x - diameter * 0.5f),
                (int) (point.y - diameter * 0.5f),
                (int) diameter,
                (int) diameter
        );
    }

    /**
     * Debug circle.
     *
     * Used for debugging
     *
     * @param pos circle position in world points
     * @param radius radius of the circle in world units
     */
    public void drawCircleDebug(Vector2 pos, float radius) {
        Vector2 point = worldPointToScreenPoint(pos);
        int diameter = (int) (radius * 2f * getPixelsPerUnit());
        view.setColor(Color.RED);
        view.drawOval(
                (int) (point.x - diameter * 0.5f),
                (int) (point.y - diameter * 0.5f),
                (int) diameter,
                (int) diameter
        );
    }

    /**
     * Draw polygon.
     *
     * Used for drawing water
     *
     * @param points game world points of the polygon
     * @param color color
     */
    public void drawPolygon(Vector2[] points, Color color) {
        view.setColor(color);
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            Vector2 screenPoint = worldPointToScreenPoint(points[i]);
            xPoints[i] = (int) screenPoint.x;
            yPoints[i] = (int) screenPoint.y;
        }
        view.fillPolygon(xPoints, yPoints, points.length);
    }

    /**
     * Debug polygon.
     *
     * Used for debugging
     *
     * @param points game world points of the polygon
     */
    public void drawPolygonDebug(Vector2[] points) {
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            Vector2 screenPoint = worldPointToScreenPoint(points[i]);
            xPoints[i] = (int) screenPoint.x;
            yPoints[i] = (int) screenPoint.y;
        }
        view.drawPolygon(xPoints, yPoints, points.length);
    }

    int getFps() {
        return (int) fpsClock.getTicksPerSecond();
    }

    float getPixelsPerUnit() {
        return viewSize.x / getUnitsPerWidth();
    }

    /**
     * World points to screen points.
     *
     * @param pos world point as Vector2
     * @return screen point as Vector2
     */
    Vector2 worldPointToScreenPoint(Vector2 pos) {
        Vector2 result = pos.subtract(getPos());
        float ppu = getPixelsPerUnit();
        int x = (int) ((result.x * ppu) + (viewSize.x / 2));
        int y = (int) ((-result.y * ppu) + (viewSize.y / 2));
        return new Vector2(x, y);
    }

    /**
     * Set view size.
     *
     * @param width window width
     * @param height window height
     */
    public void setView(int width, int height) {
        viewSize = new Vector2(width, height);
    }

}
