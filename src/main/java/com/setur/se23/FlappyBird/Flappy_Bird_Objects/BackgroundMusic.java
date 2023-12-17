package com.setur.se23.FlappyBird.Flappy_Bird_Objects;

public enum BackgroundMusic {
    NORMAL("background-music/normal.mp3"),
    FASTER("background-music/faster.mp3"),
    FASTEST("background-music/fastest.mp3");

    private final String filePath;

    private BackgroundMusic(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
