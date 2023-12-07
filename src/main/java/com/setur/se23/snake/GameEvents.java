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
                System.out.println("move up");
                break;
            case "Left":
                player.movingLeft(true);
                System.out.println("move left");
                break;
            case "Down":
                player.movingDown(true);
                System.out.println("move down");
                break;
            case "Right":
                player.movingRight(true);
                System.out.println("move right");
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
                System.out.println("move right release");
                break;
                
            default:
                break;
        }
    }
}
