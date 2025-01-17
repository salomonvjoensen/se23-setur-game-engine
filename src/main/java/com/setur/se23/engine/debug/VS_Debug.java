package com.setur.se23.engine.debug;

import com.setur.se23.dependency.render.GUI.DynamicString;
import com.setur.se23.engine.core.Time;

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

    public static final DynamicString FPSProperty = new DynamicString("0");

    private int FPS;

    private long previousTime = 0;
   
    public void checkFPS() {
        FPS += 1;

        if (1 <= (Time.getCurrentTime() - previousTime) / 1_000_000_000) {
            
            info("FPS:" + FPS);
            FPSProperty.setString(Integer.toString(FPS));

            FPS = 0;

            previousTime = Time.getCurrentTime();
        }
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
