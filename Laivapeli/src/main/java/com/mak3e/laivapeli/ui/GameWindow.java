package com.mak3e.laivapeli.ui;

import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import com.mak3e.laivapeli.engine.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * User interface.
 *
 * @author Make
 */
public class GameWindow {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * Main method.
     *
     * @param a Arguments
     * @throws InterruptedException
     */
    public static void main(String[] a) {
        System.out.println("Laivapeli\n");
        JFrame window = new JFrame("Laivapeli");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage("resources/icon.png");
        window.setIconImage(img);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.getContentPane().setLayout(new BorderLayout());
        Viewport viewport = new Viewport(WIDTH, HEIGHT);
        window.getContentPane().add(viewport, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
        while (true) {
            Core.engine.update();
            viewport.repaint();
        }
    }
}

class Viewport extends JPanel implements ComponentListener, KeyEventDispatcher {

    KeyboardFocusManager manager;

    public Viewport(int width, int height) {
        Camera.main.setView(width, height);
        this.addComponentListener(this);
        manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Camera.main.setView(this.getWidth(), this.getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D view = (Graphics2D) g;
        Map<Key, Object> rhmap = new HashMap();
        rhmap.put(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rhmap.put(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        RenderingHints rh = new RenderingHints(rhmap);
        view.addRenderingHints(rh);
        Camera.main.capture(view);
        if (Core.debug) {
            Camera.main.debug(view);
        }
        Toolkit.getDefaultToolkit().sync();

    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        int x = 0;
        int y = 0;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                y = 1;
                break;
            case KeyEvent.VK_A:
                x = -1;
                break;
            case KeyEvent.VK_S:
                y = -1;
                break;
            case KeyEvent.VK_D:
                x = 1;
                break;
            case KeyEvent.VK_UP:
                y = 1;
                break;
            case KeyEvent.VK_LEFT:
                x = -1;
                break;
            case KeyEvent.VK_DOWN:
                y = -1;
                break;
            case KeyEvent.VK_RIGHT:
                x = 1;
                break;
        }
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            Input.in.setAxis(Input.in.getAxis().add(new Vector2(x, y)));
        } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            if (e.getKeyCode() == KeyEvent.VK_F1) {
                Camera.fps = !Camera.fps;
            }
            if (e.getKeyCode() == KeyEvent.VK_F3) {
                Core.debug = !Core.debug;
                Camera.fps = Core.debug;
            }
            Input.in.setAxis(Input.in.getAxis().subtract(new Vector2(x, y)));
        }
        return false;
    }
}
