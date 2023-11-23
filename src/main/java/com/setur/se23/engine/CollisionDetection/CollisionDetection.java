package com.setur.se23.engine.CollisionDetection;

import com.setur.se23.engine.core.Entity;

public class CollisionDetection {
    /**
     * @param a an entity
     * @param b a second entity
     * @return return true if a collides with b using aabb collision detection
     */
    public static boolean checkForCollision(Entity a, Entity b) {
        return a.getX() + a.getWidth()  > b.getX() &&
               b.getX() + b.getWidth()  > a.getX() &&
               a.getY() + a.getHeight() > b.getY() &&
               b.getY() + b.getHeight() > a.getY();
    }
}