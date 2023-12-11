package com.setur.se23.engine.audio;

public enum SoundEffects {
    DIE("/die.mp3"),
    FLAP("flap.mp3"),
    HIT("hit.mp3"),
    POINT("point.mp3"),
    SWOOSH("swoosh.mp3");

    private final String filePath;

    SoundEffects(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        System.out.println("Frá SoundEffects: " + filePath);
        return filePath;
    }
}