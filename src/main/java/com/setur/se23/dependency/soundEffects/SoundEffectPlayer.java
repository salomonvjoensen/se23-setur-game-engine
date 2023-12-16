package com.setur.se23.dependency.soundEffects;

import javafx.scene.media.AudioClip;

public class SoundEffectPlayer {
    private AudioClip soundEffect;

    public SoundEffectPlayer(String relativePath) {
        soundEffect = new AudioClip(relativePath);
    }

    public void play() {
        soundEffect.play();
    }
}
