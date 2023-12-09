package com.setur.se23.snake;

import com.setur.se23.snake.Snake_Objects.SnakeHead;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.input.InputEvents;

public class GameEvents implements InputEvents {

    private SnakeHead player;
    private Runnable restart;

    public GameEvents(SnakeHead player, Runnable restart) {
        this.player = player;
        this.restart = restart;
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
            case "Up":
                player.movingUp(true);
                break;
            case "Left":
                player.movingLeft(true);
                break;
            case "Down":
                player.movingDown(true);
                break;
            case "Right":
                player.movingRight(true);
                break;
            case "Up_Ready":
                player.movingUp(false);
                break;
            case "Left_Ready":
                player.movingLeft(false);
                break;
            case "Down_Ready":
                player.movingDown(false);
                break;
            case "Right_Ready":
                player.movingRight(false);
                break;
                
            default:
                break;
        }
    }
}
