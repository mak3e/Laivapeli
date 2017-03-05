/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mak3e.laivapeli.engine;

import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Sound player.
 *
 * @author Make
 */
public class Sound {

    public static Sound audio = new Sound();
    private Map<String, Clip> playing = new HashMap();

    /**
     * Play a sound.
     *
     * @param sound sound name
     * @param loop if true, loop the sound
     */
    public void play(String sound, boolean loop) {
        try {
            Clip clip = null;
            if (playing.containsKey(sound)) {
                clip = playing.get(sound);
            } else {
                clip = AudioSystem.getClip(null);
                clip.open(Resources.files.getAudio(sound));
                playing.put(sound, clip);
            }
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.setMicrosecondPosition(0);
            }
            clip.start();
        } catch (Exception ex) {
            if (Resources.files.getStatus()) {
                System.out.println("Error while playing '" + sound + "'");
            }
        }

    }

    /**
     * Stop playing a sound.
     *
     * @param sound sound name
     */
    public void stop(String sound) {
        if (playing.containsKey(sound)) {
            playing.get(sound).stop();
        }
    }

}
