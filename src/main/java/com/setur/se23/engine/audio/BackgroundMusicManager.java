package com.setur.se23.engine.audio;

import com.setur.se23.engine.core.Core;
import com.setur.se23.dependency.backgroundMusic.AudioPlayer;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

/**
 * Unfinished class.
 */
public class BackgroundMusicManager {
    private static Map<String, String> backgroundMusicList = new HashMap<>();

    private static AudioPlayer audioPlayer;

    private static double playbackSpeed = 1.0;
    private static double playbackSpeedIncrement = 0.25;

    public static void loadBackgroundMusic(String shortPathToBackgroundMusic) {
        String mediumPath = Core.getResourcePathNonPreappended(shortPathToBackgroundMusic);
        File file = new File(mediumPath);
        String fullPath = file.toURI().toString();

        backgroundMusicList.put(shortPathToBackgroundMusic, fullPath);
    }

    public static void playLoaded(String shortPathToBackgroundMusic) {
        String fullPath = backgroundMusicList.get(shortPathToBackgroundMusic);

        if (audioPlayer != null) {
            audioPlayer.stop();
            audioPlayer.newMedia(fullPath);
        } else {
            audioPlayer = new AudioPlayer(fullPath);
        }

        audioPlayer.play();
    }

    public static void normalSpeed() {
        playbackSpeed = 1.0;
        audioPlayer.setSpeed(playbackSpeed);
    }

    public static void speedMusic() {
        playbackSpeed += playbackSpeedIncrement;
        audioPlayer.setSpeed(playbackSpeed);
    }
}
