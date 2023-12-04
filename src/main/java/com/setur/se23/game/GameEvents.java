package com.setur.se23.game;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.debug.CollisionTestObject;
import com.setur.se23.game.Flappy_Bird_Objects.Bird;

public class GameEvents {

    private Bird player;
    private Runnable restart;
    private CollisionTestObject test;

    public GameEvents(Bird player, CollisionTestObject test, Runnable restart) {
        this.player = player;
        this.restart = restart;
        this.test = test;
    }

    public void event(String event) {
        switch (event) {
            case "Jump":
                player.jump();
                break;
            case "Jump_Ready":
                player.jumpReady = true;
                break;
            case "toggle_FPS_Counter":
                if (Core.FPS_Counter) {
                    Core.FPS_Counter = false;
                } else {
                    Core.FPS_Counter = true;
                }
                break;
            case "toggle_Gizmos":
                if (Core.renderGizmos) {
                    Core.renderGizmos = false;
                } else {
                    Core.renderGizmos = true;
                }
                break;
            case "Restart":
                restart.run();
                break;

            case "W":
                test.movingUp(true);
                break;
            case "A":
                test.movingLeft(true);
                break;
            case "S":
                test.movingDown(true);
                break;
            case "D":
                test.movingRight(true);
                break;
            case "W_r":
                test.movingUp(false);
                break;
            case "A_r":
                test.movingLeft(false);
                break;
            case "S_r":
                test.movingDown(false);
                break;
            case "D_r":
                test.movingRight(false);
                break;
                
            default:
                break;
        }
    }
}
