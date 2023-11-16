package com.setur.se23.engine.loop;

import com.setur.se23.Globals;
import com.setur.se23.engine.render.Renderer;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class FX_FrameUpdate extends AnimationTimer {

    private long previousTime = 0;

    private GameEngineLoop loop = new GameEngineLoop();

    public FX_FrameUpdate() {
        Globals.mainStage.getScene().setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.A) {
                loop._xDir = -1;
                loop._yDir = 0;
            } else if (code == KeyCode.D) {
                loop._xDir = 1;
                loop._yDir = 0;
            }

            if (code == KeyCode.W) {
                loop._yDir = -1;
                loop._xDir = 0;
            } else if (code == KeyCode.S) {
                loop._yDir = 1;
                loop._xDir = 0;
            }
        });
    }
    
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
    }
}
