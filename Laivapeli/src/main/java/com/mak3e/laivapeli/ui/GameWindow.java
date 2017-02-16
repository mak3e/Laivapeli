package com.mak3e.laivapeli.ui;

import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import com.mak3e.laivapeli.engine.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * User interface
 *
 * @author Make
 */
public class GameWindow {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public static void main(String[] a) throws InterruptedException {
        JFrame window = new JFrame("Laivapeli");
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

class Viewport extends JPanel implements ComponentListener, KeyListener {
    
    public Viewport(int width, int height) {
        Camera.main.setView(width, height);
        this.addComponentListener(this);
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
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
