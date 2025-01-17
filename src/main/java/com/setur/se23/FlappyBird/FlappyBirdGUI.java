package com.setur.se23.FlappyBird;

import com.setur.se23.dependency.render.GUI.GUI;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.RenderConfig;
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

    public static void setGUI() {

        GUI.AddText(20, 20, Score.scoreProperty, 50, 5, "#FFFFFF", null);

        if (gameover) {
            GUI.AddText(Core.getWindowWidth() / 2 - 125, 150, "Game Over", 40, 10, "#FFF000", "#A00000");

            GUI.AddButton(Core.getWindowWidth() / 2 - 60, 
                          250, 
                          "Restart",
                          100,
                          30,
                          20,
                          restart);
        }

        if (RenderConfig.FPS_Counter) {
            GUI.AddText(20, Core.getWindowHeight() - 120, VS_Debug.FPSProperty, 50, 5, "#FFFFFF", "#000000");
        }

        GUI.loadGUI();
    }
}
