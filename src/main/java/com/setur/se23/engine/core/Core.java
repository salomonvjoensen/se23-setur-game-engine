package com.setur.se23.engine.core;

import com.setur.se23.engine.debug.Debug;

public class Core {

    public static Debug debug;

    public static double WindowWidth;
    public static double WindowHeight;

    /**
     * Method for getting window width.
     * 
     * @return window width
     */
    public static double getWindowWidth() {
        return WindowWidth;
    }

    /**
     * Method for getting window height.
     * 
     * @return window height
     */
    public static double getWindowHeight() {
        return WindowHeight;
    }
}
