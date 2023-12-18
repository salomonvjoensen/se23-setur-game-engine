package com.setur.se23.engine.audio;

public interface AudioPipelineInterface {
    public void newMedia(String fullPath);

    public void play();

    public void stop();

    public void pause();

    public void setSpeed(double speed);

    public void setVolume(double volume);

    public boolean isPlaying();
}
