package com.setur.se23.engine.core;

import com.setur.se23.engine.debug.Debug;
import com.setur.se23.engine.debug.VS_Debug;

public class Core {

    public static Debug debug = new VS_Debug();

    public static double WindowWidth;
    public static double WindowHeight;

    public static double getWindowWidth() {
        return WindowWidth;
    }

    public static double getWindowHeight() {
        return WindowHeight;
    }
}
