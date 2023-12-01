package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Entity;

public class CollisionDetection {
    /**
     * @param a an entity
     * @param b a second entity
     * @return return true if a collides with b using collision detection
     */
    public static boolean checkForCollision(Entity firstEntity, Entity secondEntity) {

        Collider a = ((Collidable) firstEntity).getCollider();
        Collider b = ((Collidable) secondEntity).getCollider();

        if (a instanceof SquareCollider && b instanceof SquareCollider) {
            return squaresCollide((SquareCollider) a, (SquareCollider) b);
        }

        if (a instanceof CircleCollider && b instanceof CircleCollider) {
            return circlesCollide((CircleCollider) a, (CircleCollider) b);
        }

        if (a instanceof SquareCollider && b instanceof CircleCollider) {
            return squareCircleCollide((SquareCollider) a, (CircleCollider) b);
        }

        if (a instanceof CircleCollider && b instanceof SquareCollider) {
            return squareCircleCollide((SquareCollider) b, (CircleCollider) a);
        }

        return false;
    }

    private static boolean squaresCollide(SquareCollider a, SquareCollider b) {
        return a.getX() + a.getColliderWidth()  > b.getX() &&
               b.getX() + b.getColliderWidth()  > a.getX() &&
               a.getY() + a.getColliderHeight() > b.getY() &&
               b.getY() + b.getColliderHeight() > a.getY();
    }

    private static boolean circlesCollide(CircleCollider a, CircleCollider b) {

        double distance = Math.sqrt(Math.pow(a.getCenterX() - b.getCenterX(), 2) + 
                                    Math.pow(a.getCenterY() - b.getCenterY(), 2));

        return distance < a.getColliderRadius() + b.getColliderRadius();
    }

    private static boolean squareCircleCollide(SquareCollider a, CircleCollider b) {

        double closestX = closestCoords(a.getX(), a.getX() + a.getColliderWidth(), b.getCenterX());
        double closestY = closestCoords(a.getY(), a.getY() + a.getColliderHeight(), b.getCenterY());

        double distance = Math.sqrt(Math.pow(b.getCenterX() - closestX, 2) + 
                                    Math.pow(b.getCenterY() - closestY, 2));

        return distance < b.getColliderRadius();
    }

    private static double closestCoords(double min, double max, double circleCenter) {
        return Math.max(min, Math.min(max, circleCenter));
    }
}