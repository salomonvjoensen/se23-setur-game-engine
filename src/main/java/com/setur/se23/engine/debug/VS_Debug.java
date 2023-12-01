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
            
            info("FPS:" + FPS);

            FPS = 0;

            previousTime = Core.getCurrentTime();
        }
    }
}
