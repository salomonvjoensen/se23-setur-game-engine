package com.setur.se23.engine.loop;

import com.setur.se23.engine.core.Core;

import javafx.animation.AnimationTimer;

public abstract class FX_FrameUpdate extends AnimationTimer {

    public abstract void logicLoop(double deltaTimeS);

    private long previousTime = 0;
    
    @Override
    public void handle(long currentTime) {

        if (previousTime == 0) {
            previousTime = currentTime;
        }

        double deltaTimeNs = currentTime - previousTime;
        double deltaTimeS = deltaTimeNs / 1_000_000_000.0;

        // Call your update method to update the game state based on deltaTime
        logicLoop(deltaTimeS);

        previousTime = currentTime;
        Core.currentTime = currentTime;
    }
}
