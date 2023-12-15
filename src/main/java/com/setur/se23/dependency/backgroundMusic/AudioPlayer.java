package com.setur.se23.dependency.backgroundMusic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer {
    private static Media media;
    private static MediaPlayer mediaPlayer;

    public AudioPlayer(String fullPath) {
        newMedia(fullPath);
    }

    public void newMedia(String fullPath) {
        media = new Media(fullPath);
        mediaPlayer = new MediaPlayer(media);
    }

    public void play() {
        mediaPlayer.play();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void setSpeed(double speed) {
        mediaPlayer.setRate(speed);
    }
}
