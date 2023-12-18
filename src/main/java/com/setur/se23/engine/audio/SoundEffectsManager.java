package com.setur.se23.engine.audio;

import java.util.Map;
import java.util.HashMap;

import com.setur.se23.dependency.audio.FX_SoundEffectPlayer;
import com.setur.se23.engine.core.Resource;

public class SoundEffectsManager {
    private static Map<String, FX_SoundEffectPlayer> soundEffectsMap = new HashMap<>();

    /**
     * Pre-load sound effect into memory for quick playback.
     * 
     * @param shortPathToSoundEffect
     */
    public static void loadSoundEffect(String shortPathToSoundEffect) {
        FX_SoundEffectPlayer soundEffect = new FX_SoundEffectPlayer(
                Resource.getResourcePath(shortPathToSoundEffect));
        soundEffectsMap.put(shortPathToSoundEffect, soundEffect);
    }

    /**
     * Play pre-loaded sound effect.
     * 
     * @param shortPathToSoundEffect
     */
    public static void playLoaded(String shortPathToSoundEffect) {
        FX_SoundEffectPlayer soundEffect = soundEffectsMap.get(
                shortPathToSoundEffect);
        soundEffect.play();
    }

    /**
     * Unused, laggy.
     * Could be used for non-immediate or seldom used sound effects.
     * 
     * @param soundEffectPath
     */
    public void playNonLoaded(String soundEffectPath) {

        String path = Resource.getResourcePath(soundEffectPath);

        try {
            FX_SoundEffectPlayer soundEffect = new FX_SoundEffectPlayer(path);
            soundEffect.play();
        } catch (Exception e) {
            System.out.println("Sound file not found: " + soundEffectPath);
        }
    }
}