package com.setur.se23.engine.audio;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

import com.setur.se23.engine.core.Resource;

public class BackgroundMusicManager {
    private static Map<String, String> backgroundMusicList = new HashMap<>();

    private static AudioPipelineInterface audioPlayer;

    private static double playbackSpeed = 1.0;

    private static double volume = 0.8;

    public static void initialize(AudioPipelineInterface audioPipelineInterface) {
        BackgroundMusicManager.audioPlayer = audioPipelineInterface;
    }

    /**
     * Loading background music will load its full path
     * into a Map later to be played. 
     * @param shortPathToBackgroundMusic
     */
    public static void loadBackgroundMusic(String shortPathToBackgroundMusic) {
        String mediumPath = Resource.getResourcePathNonPreappended(shortPathToBackgroundMusic);
        File file = new File(mediumPath);
        String fullPath = file.toURI().toString();

        backgroundMusicList.put(shortPathToBackgroundMusic, fullPath);
    }

    /**
     * Playing loaded music instantiates based on path.
     * @param shortPathToBackgroundMusic
     */
    public static void playLoaded(String shortPathToBackgroundMusic) {
        String fullPath = backgroundMusicList.get(shortPathToBackgroundMusic);

        if (audioPlayer.isPlaying()) {
            stop();
        }
        audioPlayer.newMedia(fullPath);
        setVolume(volume);

        play();
    }

    /**
     * Play music.
     */
    public static void play() {
        audioPlayer.play();
    }

    /**
     * Pause music.
     */
    public static void pause() {
        audioPlayer.pause();
    }

    /**
     * Stop music.
     */
    public static void stop() {
        audioPlayer.stop();
    }

    /**
     * Set back to normal playback speed.
     */
    public static void normalSpeed() {
        playbackSpeed = 1.0;
        audioPlayer.setSpeed(playbackSpeed);
    }

    /**
     * Sets the speed of playback.
     */
    public static void setPlaybackSpeed(double playbackSpeed) {
        audioPlayer.setSpeed(playbackSpeed);
    }

    /**
     * Sets volume.
     * Initial volume is 0.8, i.e., 80 %.
     */
    public static void setVolume(double volume) {
        BackgroundMusicManager.volume = volume;
        audioPlayer.setVolume(volume);
    }
}
