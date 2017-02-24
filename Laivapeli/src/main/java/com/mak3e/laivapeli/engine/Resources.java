/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 * Resource loader, loads resources.
 *
 * @author Make
 */
public class Resources {

    public static Resources files = new Resources();
    private Map<String, AudioInputStream> audioFiles = new HashMap();
    private Map<String, BufferedImage> imageFiles = new HashMap();
    private boolean status = false;

    /**
     * Load all resources.
     */
    public void load() throws Exception {
        System.out.println("Loading resources");

        List<String> resources = new ArrayList<>();
        resources.add("box1.png");
        resources.add("fish.png");
        resources.add("fish.wav");
        resources.add("gfish.png");
        resources.add("harbor.png");
        resources.add("icon.png");
        resources.add("idleship.wav");
        resources.add("island.png");
        resources.add("ocean.wav");
        resources.add("rain.wav");
        resources.add("ship.png");
        resources.add("ship.wav");
        resources.add("smoke.png");

        for (String file : resources) {
            System.out.println("  " + file);
            try {
                if (file.contains(".wav")) {
                    loadAudio(file);
                }
                if (file.contains(".png")) {
                    loadImage(file);
                }
            } catch (Exception ex) {
                System.out.println(
                        "Error while loading '" + file + "'"
                );
            }
        }
        System.out.println("Done");
        status = true;
    }

    /**
     * Get resource status.
     *
     * @return true if all resources are loaded
     */
    public boolean getStatus() {
        return status;
    }

    void loadAudio(String file) throws Exception {
        AudioInputStream audioFile = null;
        audioFile = AudioSystem.getAudioInputStream(
                System.class.getResource("/" + file)
        );
        audioFiles.put(file.substring(0, file.length() - 4), audioFile);
    }

    void loadImage(String file) throws Exception {
        BufferedImage imageFile = null;
        imageFile = ImageIO.read(System.class.getResourceAsStream("/" + file));
        imageFiles.put(file.substring(0, file.length() - 4), imageFile);
    }

    /**
     * Get audio data.
     *
     * @param name sound name
     * @return audio data
     */
    public AudioInputStream getAudio(String name) {
        return audioFiles.get(name);
    }

    /**
     * Get sprite data.
     *
     * @param name sprite name
     * @return sprite data
     */
    public BufferedImage getImage(String name) {
        return imageFiles.get(name);
    }

}
