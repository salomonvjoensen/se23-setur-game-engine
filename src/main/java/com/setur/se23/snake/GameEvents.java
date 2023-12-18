package com.setur.se23.snake;

import com.setur.se23.snake.Snake_Objects.SnakeHead;
import com.setur.se23.engine.core.RenderConfig;
import com.setur.se23.engine.input.InputEvents;

/**
 * The events in the game determined by keyboard inputs.
 */
public class GameEvents implements InputEvents {

    private SnakeHead player;
    private SnakeGame game;

    /**
     * Constructor.
     * 
     * @param player The SnakeHead
     * @param game Reference to the game itself.
     */
    public GameEvents(SnakeHead player, SnakeGame game) {
        this.player = player;
        this.game = game;
    }

    public void event(String event) {
        switch (event) {
            case "toggle_FPS_Counter":
                RenderConfig.toggleFPS_Counter();
                break;
            case "toggle_Gizmos":
                RenderConfig.toggleRenderGizmos();
                break;
            case "Restart":
                game.initSnakeAndObjects();
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
                game.isFirstMove(false);
                break;
        }
    }
}
