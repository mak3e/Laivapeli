/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 * Resource loader, loads resources.
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
    public void load() {
        System.out.println("Loading resources");
        File[] list = new File("resources").listFiles();
        for (File file : list) {
            System.out.println("  " + file.getName());
            try {
                if (file.getName().contains(".wav")) {
                    loadAudio(file);
                }
                if (file.getName().contains(".png")) {
                    loadImage(file);
                }
            } catch (Exception ex) {
                System.out.println(
                        "Error while loading '" + file.getName() + "'"
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

    void loadAudio(File file) throws Exception {
        AudioInputStream audioFile = null;
        audioFile = AudioSystem.getAudioInputStream(file);
        audioFiles.put(getName(file), audioFile);
    }

    void loadImage(File file) throws Exception {
        BufferedImage imageFile = null;
        imageFile = ImageIO.read(file);
        imageFiles.put(getName(file), imageFile);
    }

    String getName(File file) {
        return file.getName().substring(0, file.getName().lastIndexOf("."));
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
