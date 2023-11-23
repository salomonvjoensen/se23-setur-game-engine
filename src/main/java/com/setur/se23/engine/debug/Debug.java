package com.setur.se23.engine.debug;

public class Debug {
    
    public static void warning(String string) {
        System.out.println("\033[33m Warning: " + string + "\033[0m");
    }

    public static void error(String string) {
        System.out.println("\033[31m Error: " + string + "\033[0m");
    }

    public static void info(String string) {
        System.out.println(" Info: " + string);
    }
}
