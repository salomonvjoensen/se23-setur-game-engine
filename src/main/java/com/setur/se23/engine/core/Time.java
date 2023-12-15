package com.setur.se23.engine.core;

public class Time {
    
    public static long currentNanoTime;

    public static long getCurrentTime() {
        return currentNanoTime;
    }

    public static void updateTime(long currentNanoTime) {
        Time.currentNanoTime = currentNanoTime;;
    }
}
