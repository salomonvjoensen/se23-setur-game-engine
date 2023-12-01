package com.setur.se23.game;

import com.setur.se23.engine.core.Core;
import com.setur.se23.game.Flappy_Bird_Objects.Bird;

public class GameEvents {

    private Bird player;
    private Runnable restart;

    public GameEvents(Bird player, Runnable restart) {
        this.player = player;
        this.restart = restart;
    }

    public void event(String event) {
        switch (event) {
            case "Jump":
                player.jump();
                break;
            case "Jump_Ready":
                player.jumpReady = true;
                break;
            case "FPS_Counter":
                if (Core.FPS_Counter) {
                    Core.FPS_Counter = false;
                } else {
                    Core.FPS_Counter = true;
                }
                break;
            case "Restart":
                restart.run();
                break;
                
            default:
                break;
        }
    }
}
