package com.setur.se23.dependency.audio;

import javafx.scene.media.AudioClip;

public class FX_SoundEffectPlayer {
    private AudioClip soundEffect;

    public FX_SoundEffectPlayer(String relativePath) {
        soundEffect = new AudioClip(relativePath);
    }

    public void play() {
        soundEffect.play();
    }
}
