package com.setur.se23.FlappyBird;

import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Background;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Pipe;
import com.setur.se23.dependency.render.GUI.DynamicString;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.loop.Loop;

public class Score {
    
    public static final DynamicString scoreProperty = new DynamicString("0");

    public static int score;
    
    public static void updateScore(int amount) {
        score += amount;
        scoreProperty.setString(Integer.toString(score));

        updateGame();
    }
   
    public static void resetScore() {
        score = 0;
        scoreProperty.setString(Integer.toString(score));
    }

    private static void updateGame() {
        
        Background background = (Background) Loop.entities.get(0);

        if (score % 30 == 0) {
            background.changeMaterial(1);

            // change music
        }
        
        else if (score % 20 == 0) {
            background.changeMaterial(0);

            // change music
        }
        
        else if (score % 10 == 0) {
            background.changeMaterial(1);

            // change music
        }

        if (score % 10 == 0) {
            Pipe.speed += 25;
            Core.debug.info("Pipe speeding up (pipe speed: " + Pipe.speed + ")");
        }
    }
}
