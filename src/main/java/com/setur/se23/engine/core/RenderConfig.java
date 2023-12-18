package com.setur.se23.engine.core;

public class RenderConfig {
    
    public static boolean FPS_Counter;

    public static boolean renderGizmos;

    public static void toggleFPS_Counter() {
        if (FPS_Counter) {
            FPS_Counter = false;
        } else {
            FPS_Counter = true;
        }
    }

    public static void toggleRenderGizmos() {
        if (renderGizmos) {
            renderGizmos = false;
        } else {
            renderGizmos = true;
        }
    }
}
