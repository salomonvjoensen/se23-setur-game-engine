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
            case "W":
                player.movingUp(true);
                System.out.println("move up");
                break;
            case "A":
                player.movingLeft(true);
                System.out.println("move left");
                break;
            case "S":
                player.movingDown(true);
                System.out.println("move down");
                break;
            case "D":
                player.movingRight(true);
                System.out.println("move right");
                break;
            case "W_r":
                player.movingUp(false);
                break;
            case "A_r":
                player.movingLeft(false);
                break;
            case "S_r":
                player.movingDown(false);
                break;
            case "D_r":
                player.movingRight(false);
                break;
                
            default:
                break;
        }
    }
}
