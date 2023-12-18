package com.setur.se23.dependency.audio;

import com.setur.se23.engine.audio.AudioPipelineInterface;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class FX_AudioPlayer implements AudioPipelineInterface {
    private static Media media;
    private static MediaPlayer mediaPlayer;

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

    public void pause() {
        mediaPlayer.pause();
    }

    public void setSpeed(double speed) {
        mediaPlayer.setRate(speed);
    }

    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    public boolean isPlaying() {
        return media != null;
    }
}
