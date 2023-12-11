package com.setur.se23.FlappyBird.Flappy_Bird_Objects;

public enum SoundEffects {
    DIE("sound-effects/die.mp3"),
    FLAP("sound-effects/flap.mp3"),
    HIT("sound-effects/hit.mp3"),
    POINT("sound-effects/point.mp3"),
    SWOOSH("sound-effects/swoosh.mp3");

    private final String filePath;

    private SoundEffects(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}