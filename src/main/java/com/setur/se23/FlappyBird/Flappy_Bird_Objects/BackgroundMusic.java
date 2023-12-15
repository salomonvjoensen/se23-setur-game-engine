package com.setur.se23.FlappyBird.Flappy_Bird_Objects;

public enum BackgroundMusic {
    MUSIC_FOLDER("background-music");

    private final String filePath;

    private BackgroundMusic(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
