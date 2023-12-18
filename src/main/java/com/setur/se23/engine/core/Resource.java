package com.setur.se23.engine.core;

public class Resource {
    
    /**
     * Method for getting resource path.
     * 
     * @param resource resource path from resource folder
     * @return resource path
     */
    public static String getResourcePath(String resource) {
        return "file:src/main/resources/" + resource;
    }

    /**
     * Method for getting resource path (needed for fx audio).
     * 
     * @param resource resource path from resource folder
     * @return resource path
     */
    public static String getResourcePathNonPreappended(String resource) {
        return "src/main/resources/" + resource;
    }

    /**
     * Method for getting sprite resource path.
     * 
     * @param sprite sprite path from sprites folder
     * @return sprite path
     */
    public static String getSprite(String sprite) {
        return "file:src/main/resources/sprites/" + sprite;
    }
}
