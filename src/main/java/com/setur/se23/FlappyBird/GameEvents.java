package com.setur.se23.FlappyBird;

import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Bird;
import com.setur.se23.engine.core.RenderConfig;
import com.setur.se23.engine.input.InputEvents;

public class GameEvents implements InputEvents {

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
                player.jumpIsReady();
                break;
            case "toggle_FPS_Counter":
                RenderConfig.toggleFPS_Counter();
                FlappyBirdGUI.setGUI();
                break;
            case "toggle_Gizmos":
                RenderConfig.toggleRenderGizmos();
                break;
            case "Restart":
                restart.run();
                break;
                
            default:
                break;
        }
    }
}
