package com.setur.se23.game;

import com.setur.se23.engine.core.Core;
import com.setur.se23.game.Flappy_Bird_Objects.Bird;

public class GameEvents {

    private Bird player;

    public GameEvents(Bird player) {
        this.player = player;
        //player.setAngle(90);
    }
    
    public void event(String event) {
        switch (event) {
            case "Jump":
                player.jump();
                break;
            case "FPS_Counter":
                if (Core.FPS_Counter) {
                    Core.FPS_Counter = false;
                } else {
                    Core.FPS_Counter = true;
                }
                
            default:
                break;
        }
    }
}
