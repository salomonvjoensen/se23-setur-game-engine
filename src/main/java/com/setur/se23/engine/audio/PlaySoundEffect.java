package com.setur.se23.engine.audio;

import javafx.scene.media.AudioClip;
import java.net.URL;

public class PlaySoundEffect {
    public void play(SoundEffects soundEffect) {
        URL resource = getClass().getResource(soundEffect.getFilePath());
        System.out.println("Frá PlaySoundEffect1: " + soundEffect.getFilePath());
        System.out.println("Frá PlaySoundEffect2: " + resource);
        if (resource != null) {
            AudioClip audioClip = new AudioClip(resource.toString());
            audioClip.play();
        } else {
            System.out.println("Sound file not found: " + soundEffect.getFilePath());
        }
    }
}