package com.setur.se23.game;

import com.setur.se23.engine.core.Core;
import com.setur.se23.game.Flappy_Bird_Objects.Bird;

public class GameEvents {

    private long previousTime = 0;
    
    public void event(String event, Bird player) {
        switch (event) {
            case "Jump":
                if (0.2 < (Core.getCurrentTime() - previousTime) / 1_000_000_000.0) {
                    player.setYDir(-200);
                    player.setFallSpeed(10);
                    previousTime = Core.getCurrentTime();
                }
                break;
        
            default:
                break;
        }
    }
}
