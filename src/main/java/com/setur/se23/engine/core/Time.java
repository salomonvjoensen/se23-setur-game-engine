package com.setur.se23.engine.core;

public class Time {
    
    private static long currentNanoTime;

    /**
     * Method for getting current time.
     * 
     * @return current time in nano seconds
     */
    public static long getCurrentTime() {
        return currentNanoTime;
    }

    public static void updateTime(long currentNanoTime) {
        Time.currentNanoTime = currentNanoTime;;
    }
}
