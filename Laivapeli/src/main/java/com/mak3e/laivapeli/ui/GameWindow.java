package com.mak3e.laivapeli.ui;

import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import com.mak3e.laivapeli.engine.*;

public class GameWindow {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public static void main(String[] a) {
        JFrame window = new JFrame("Laivapeli");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(30, 30, WIDTH, HEIGHT);
        Viewport viewport = new Viewport(WIDTH, HEIGHT);
        window.getContentPane().add(viewport);
        window.setVisible(true);
        while (true) {
            //Engine and drawing are independent
            Core.engine.update();
            viewport.repaint();
        }
    }
}

class Viewport extends JPanel {

    public Viewport(int width, int height) {
        Camera.main.setView(width, height);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D view = (Graphics2D) g;
        Camera.main.capture(view);
    
    }
}
