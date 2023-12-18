package com.setur.se23.FlappyBird;

import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Background;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.BackgroundMusic;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Pipe;
import com.setur.se23.dependency.render.GUI.DynamicString;
import com.setur.se23.engine.audio.BackgroundMusicManager;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.loop.Loop;

public class Score {
    
    public static final DynamicString scoreProperty = new DynamicString("0");

    public static int score;

    public static int level = 0;

    public static double playbackSpeed = 1.0;
    
    public static void updateScore(int amount) {
        score += amount;
        scoreProperty.setString(Integer.toString(score));

        updateGame();
    }
   
    public static void resetScore() {
        score = 0;
        level = 0;
        scoreProperty.setString(Integer.toString(score));
    }

    private static void updateGame() {
        
        Background background = (Background) Loop.entities.get(0);

        if (score % 10 == 0) {
            Pipe.speed += 25;
            Core.debug.info("Pipe speeding up (pipe speed: " + Pipe.speed + ")");
            level++;
            if (level > 2) {
                playbackSpeed += 0.1;
                BackgroundMusicManager.setPlaybackSpeed(playbackSpeed);
                if (level % 2 == 0) {
                    background.changeMaterial(0);
                } else {
                    background.changeMaterial(1);
                }
            } else if (level == 2) {
                BackgroundMusicManager.playLoaded(BackgroundMusic.FASTEST.getFilePath());
                background.changeMaterial(0);
            } else if (level == 1) {
                BackgroundMusicManager.playLoaded(BackgroundMusic.FASTER.getFilePath());
                background.changeMaterial(1);
            }
        }
    }
}
