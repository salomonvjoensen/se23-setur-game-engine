package com.setur.se23.engine.loop;

import com.setur.se23.Globals;
import com.setur.se23.game.GameLoop;

import javafx.animation.AnimationTimer;

public class FX_FrameUpdate extends AnimationTimer {

    private long previousTime = 0;

    private GameLoop loop = new GameLoop();
    
    @Override
    public void handle(long currentTime) {

        if (previousTime == 0) {
            previousTime = currentTime;
        }

        double deltaTimeNs = currentTime - previousTime;
        double deltaTimeS = deltaTimeNs / 1_000_000_000.0;

        // Call your update method to update the game state based on deltaTime
        loop.update(deltaTimeS);

        previousTime = currentTime;
        Globals.currentTime = currentTime;
    }
}
