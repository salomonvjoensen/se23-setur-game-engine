package com.setur.se23.engine.audio;

import java.util.Map;
import java.util.HashMap;

import javafx.scene.media.AudioClip;

import com.setur.se23.engine.core.Core;

public class SoundEffectsManager {
    private static Map<String, AudioClip> soundEffectsMap = new HashMap<>();

    /**
     * Pre-load sound effect into memory for quick playback.
     * @param shortPathToSoundEffect
     */
    public static void addSoundEffect(String shortPathToSoundEffect) {
        soundEffectsMap.put(shortPathToSoundEffect, new AudioClip(Core.getResourcePath(shortPathToSoundEffect)));
    }

    /**
     * Play pre-loaded sound effect.
     * @param shortPathToSoundEffect
     */
    public static void playSoundEffect(String shortPathToSoundEffect) {
        soundEffectsMap.get(shortPathToSoundEffect).play();
    }

    /**
     * Unused, laggy.
     * Could be used for non-immediate or seldom used sound effects.
     * @param soundEffect
     */
    public void play(String soundEffect) {
        
        String path = Core.getResourcePath(soundEffect);

        try {
            AudioClip audioClip = new AudioClip(path);
            audioClip.play();
        } catch (Exception e) {
            System.out.println("Sound file not found: " + soundEffect);
        }
    }
}