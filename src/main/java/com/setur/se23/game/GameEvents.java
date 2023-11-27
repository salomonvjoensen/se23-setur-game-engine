package com.setur.se23.game;

import com.setur.se23.engine.core.Core;
import com.setur.se23.game.Flappy_Bird_Objects.Bird;

public class GameEvents {

    private long previousTime = 0;
    private Bird player;

    public GameEvents(Bird player) {
        this.player = player;
        player.setAngle(90);
    }
    
    public void event(String event) {
        switch (event) {
            case "Jump":
                if (0.3 < (Core.getCurrentTime() - previousTime) / 1_000_000_000.0) {
                    player.setVelocityY(-200);
                    player.setFallSpeed(10);
                    previousTime = Core.getCurrentTime();
                    player.setAngle(player.getAngle() + 10);
                }
                break;
        
            default:
                break;
        }
    }
}
