package com.setur.se23.engine.audio;

import com.setur.se23.engine.core.Core;
import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Unfinished class.
 */
public class Music {
    List<String> songs1 = Arrays.asList(
            "file:///path/to/your/song1.mp3",
            "file:///path/to/your/song2.mp3",
            "file:///path/to/your/song3.mp3");

    private Media media;
    private static MediaPlayer mediaPlayer;

    private File directory;
    private File[] files;

    private ArrayList<File> songs;

    private int songNumber;

    private boolean running;

    public Music(String shortPathToMusic) {
        songs = new ArrayList<File>();

        directory = new File(Core.getResourcePathNonPreappended(shortPathToMusic));
        System.out.println("Mappa: " + directory);

        files = directory.listFiles();

        
        if (files != null) {
            
            for (File file : files) {
                songs.add(file);
                System.out.println("Navn á fílu: " + file.getName());
            }
            
            songNumber = 0;
            System.out.println("Songur 0: " + songs.get(songNumber).getName());

            System.out.println("Slóð til songs: " + songs.get(songNumber).toURI().toString());
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
        } else {
            System.out.println("No music found.");
        }

        // try {
        // File audioFile = new
        // File(Core.getResourcePathNonPreappended(shortPathToMusic));
        // System.out.println("Er til: " + audioFile.exists());
        // URI uri = audioFile.toURI();
        // System.out.println("URI: " + uri);
        // System.out.println("URI: " + uri.normalize());
        // System.out.println("Hálvlong slóð: " +
        // Core.getResourcePathNonPreappended(shortPathToMusic));
        // media = new Media(uri.toString());
        // System.out.println("SPOR: " + media.getTracks());
        // mediaPlayer = new MediaPlayer(media);
        // mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // // String longPath = Core.getResourcePath(shortPathToMusic);
        // // String soundFile = getClass().getResource("file:../../../../../../" +
        // "resources/" + shortPathToMusic).toString();
        // // // String soundFile = getClass().getResourceAsStream("../../../../../../"
        // + longPath).toString();
        // // if (soundFile == null) {
        // // throw new IllegalArgumentException("File not found: " + shortPathToMusic);
        // // }

        // // AudioClip audio = new AudioClip(Core.getResourcePath(shortPathToMusic));
        // // audio.play();
        // // System.out.println(media.getSource());

        // // mediaPlayer.play();
        // // mediaPlayer.setVolume(1.0);
        // // System.out.println("ERROR: " + mediaPlayer.getError());
        // // System.out.println("STATUS: " + mediaPlayer.getStatus());
        // // System.out.println("Mute: " + mediaPlayer.isMute());
        // // mediaPlayer.setOnRepeat(null);
        // // mediaPlayer.setAutoPlay(true);

        // // mediaPlayer.setOnEndOfMedia(new Runnable() {
        // // @Override
        // // public void run() {
        // // mediaPlayer.seek(Duration.ZERO);
        // // mediaPlayer.play();
        // // }
        // // });
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        // String path = Core.getResourcePath(shortPathToMusic);
        // System.out.println(path);
        // media = new Media(path);
        // mediaPlayer = new MediaPlayer(media);
    }

    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        } else {
            System.out.println("No media player.");
        }
    }

    private void playSong(List<String> songs, int index) {
        if (index < songs.size()) {
            Media media = new Media(songs.get(index));
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(() -> playSong(songs, index + 1));
            mediaPlayer.play();
            songNumber = index;
        }
    }

    // private void nextSong() {
    // if (songNumber < songs.size() - 1) {
    // playSong(songs, songNumber + 1);
    // }
    // }

    // playSong(songs, 0); SETA Í ANNAN KLASSA
}
