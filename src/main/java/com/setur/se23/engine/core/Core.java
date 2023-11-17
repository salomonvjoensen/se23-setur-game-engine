package com.setur.se23.engine.core;

import com.setur.se23.Globals;

public class Core {
    
    public static String getResorcePath(String resource) {
        return "file:src/main/resources/" + resource;
    }

    public static double getStageWidth() {
        return Globals.mainStage.getWidth();
    }

    public static double getStageHeight() {
        return Globals.mainStage.getHeight();
    }


}
