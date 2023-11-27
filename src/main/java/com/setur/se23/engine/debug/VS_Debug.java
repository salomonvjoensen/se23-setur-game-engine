package com.setur.se23.engine.debug;

import com.setur.se23.engine.core.Core;

public class VS_Debug extends Debug {
    
    @Override
    public void warning(String string) {
        System.out.println("\033[33m Warning: " + string + "\033[0m");
    }

    @Override
    public void error(String string) {
        System.out.println("\033[31m Error: " + string + "\033[0m");
    }

    @Override
    public void info(String string) {
        System.out.println(" Info: " + string);
    }

    private int FPS;

    private long previousTime = 0;
   
    public void checkFPS() {
        FPS += 1;

        if (1 <= (Core.getCurrentTime() - previousTime) / 1_000_000_000) {
            //clearConsole();

            info("FPS:" + FPS);

            FPS = 0;

            previousTime = Core.getCurrentTime();
        }
    }

    /**
     * Clears the console, based on OS.
     */
    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO()
                    .start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception ex) {}
    } 
}
