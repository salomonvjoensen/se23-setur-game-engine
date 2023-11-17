package com.setur.se23.engine.loop;

import com.setur.se23.game.GameLoop;

import javafx.animation.AnimationTimer;

public class FX_FrameUpdate extends AnimationTimer {

    private long previousTime = 0;

    private GameLoop loop = new GameLoop();

    public FX_FrameUpdate() {
        //Globals.mainStage.getScene().setOnKeyPressed(event -> {
        //    KeyCode code = event.getCode();
        //    if (code == KeyCode.A) {
        //        loop.player.setXDir(-1);
        //        loop.player.setYDir(0);
        //    } else if (code == KeyCode.D) {
        //        loop.player.setXDir(1);
        //        loop.player.setYDir(0);
        //    }

        //    if (code == KeyCode.W) {
        //        loop.player.setYDir(-1);
        //        loop.player.setXDir(0);
        //    } else if (code == KeyCode.S) {
        //        loop.player.setYDir(1);
        //        loop.player.setXDir(0);
        //    }
        //});
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
