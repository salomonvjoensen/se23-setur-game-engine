package com.setur.se23.engine.audio;

import com.setur.se23.engine.core.Core;
import java.io.File;
import java.net.URI;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Unfinished class.
 */
public class Music {
    private static MediaPlayer mediaPlayer;
    private Media media;
    // AudioClip audioClip;

    public Music(String shortPathToMusic) {
        try {
            // String longPath = Core.getResourcePath(shortPathToMusic);
            // String soundFile = getClass().getResource("file:../../../../../../" + "resources/" + shortPathToMusic).toString();
            // // String soundFile = getClass().getResourceAsStream("../../../../../../" + longPath).toString();
            // if (soundFile == null) {
            //     throw new IllegalArgumentException("File not found: " + shortPathToMusic);
            // }

            File audioFile = new File(Core.getResourcePathNoPreappend(shortPathToMusic));
            System.out.println("Er til: " + audioFile.exists());
            URI uri = audioFile.toURI();
            System.out.println("URI: " + uri);
            System.out.println("URI: " + uri.normalize());
            System.out.println("Hálvlong slóð: " + Core.getResourcePathNoPreappend(shortPathToMusic));
            // AudioClip audio = new AudioClip(Core.getResourcePath(shortPathToMusic));
            // audio.play();
            media = new Media(uri.toString());
            System.out.println("SPOR: " + media.getTracks());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            // System.out.println(media.getSource());

            mediaPlayer.play();
            // mediaPlayer.setVolume(1.0);
            // System.out.println("ERROR: " + mediaPlayer.getError());
            // System.out.println("STATUS: " + mediaPlayer.getStatus());
            // System.out.println("Mute: " + mediaPlayer.isMute());
            // mediaPlayer.setOnRepeat(null);
            // mediaPlayer.setAutoPlay(true);
            
            // mediaPlayer.setOnEndOfMedia(new Runnable() {
            //     @Override
            //     public void run() {
            //         mediaPlayer.seek(Duration.ZERO);
            //         mediaPlayer.play();
            //     }
            // }); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        // String path = Core.getResourcePath(shortPathToMusic);
        // System.out.println(path);
        // media = new Media(path);
        // mediaPlayer = new MediaPlayer(media);
    }

    public void play() {
        mediaPlayer.play();
    }
}

