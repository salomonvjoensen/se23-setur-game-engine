package com.setur.se23.snake;

public enum SoundEffects {
    
    EAT_APPLE("sound-effects/apple-bite.mp3"),
    SNAKE_HISS("sound-effects/snake-hiss.mp3");

    private final String filePath;

    private SoundEffects(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
