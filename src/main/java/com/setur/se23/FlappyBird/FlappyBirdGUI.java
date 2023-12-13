package com.setur.se23.FlappyBird;

import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Bird;
import com.setur.se23.engine.GUI.GUI;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.debug.VS_Debug;

public class FlappyBirdGUI {

    public static boolean gameover = false;
    private static Runnable restart;

    public static void gameOver() {
        gameover = true;
    }

    public static void newGame() {
        gameover = false;
    }

    public static void setRestartRunnable(Runnable restartRunnable) {
        restart = restartRunnable;
    }

    public static void setGUI(Bird player) {

        GUI.AddText(20, 20, Score.scoreProperty, 50, 5, "#FFFFFF", null);
        //GUI.AddButton(Core.getStageWidth() / 2 - 80, 
        //              Core.getStageHeight() - 150, 
        //              100,
        //              30,
        //              "Jump",
        //              30,
        //              () -> {
        //                    player.jump();
        //                    player.jumpReady = true;
        //              });

        if (gameover) {
            GUI.AddText(Core.getStageWidth() / 2 - 125, 150, "Game Over", 40, 10, "#FFF000", "#A00000");

            GUI.AddButton(Core.getStageWidth() / 2 - 60, 
                          250, 
                          100,
                          30,
                          "Restart",
                          20,
                          restart);
        }

        if (Core.FPS_Counter) {
            GUI.AddText(20, Core.getStageHeight() - 120, VS_Debug.FPSProperty, 50, 5, "#FFFFFF", "#000000");
        }

        GUI.loadGUI();
    }
}
