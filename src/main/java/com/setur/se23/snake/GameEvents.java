package com.setur.se23.snake;

import com.setur.se23.snake.Snake_Objects.SnakeHead;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.debug.CollisionTestObject;
import com.setur.se23.engine.input.InputEvents;

public class GameEvents implements InputEvents {

    private SnakeHead player;
    private Runnable restart;
    private CollisionTestObject test;

    public GameEvents(SnakeHead player, CollisionTestObject test, Runnable restart) {
        this.player = player;
        this.restart = restart;
        this.test = test;
    }

    public void event(String event) {
        switch (event) {
            case "toggle_FPS_Counter":
                Core.toggleFPS_Counter();
                break;
            case "toggle_Gizmos":
                Core.toggleRenderGizmos();
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
