package com.setur.se23.engine.core;

public class Resource {
    
    public static String getResourcePath(String resource) {
        return "file:src/main/resources/" + resource;
    }

    public static String getSprite(String sprite) {
        return "file:src/main/resources/sprites/" + sprite;
    }
}
