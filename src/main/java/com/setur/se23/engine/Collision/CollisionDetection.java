package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Entity;

public class CollisionDetection {
    /**
     * @param a an entity
     * @param b a second entity
     * @return return true if a collides with b using collision detection
     */
    public static boolean checkForCollision(Entity entity1, Entity entity2) {

        Collider a = ((Collidable) entity1).getCollider();
        Collider b = ((Collidable) entity2).getCollider();

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
        return a.getX() + a.getWidth()  > b.getX() &&
               b.getX() + b.getWidth()  > a.getX() &&
               a.getY() + a.getHeight() > b.getY() &&
               b.getY() + b.getHeight() > a.getY();
    }

    private static boolean circlesCollide(CircleCollider circle1, CircleCollider circle2) {

        double distanceX = circle1.getCenterX() - circle2.getCenterX();
        double distanceY = circle1.getCenterY() - circle2.getCenterY();

        double distance = Math.sqrt((distanceX * distanceX) + 
                                    (distanceY * distanceY));

        return distance < (circle1.getRadius() + circle2.getRadius());
    }

    private static boolean squareCircleCollide(SquareCollider square, CircleCollider circle) {

        double leftX  = square.getX();
        double rightX = square.getX() + square.getWidth();
        double closestX = closestCoord(leftX, rightX, circle.getCenterX());

        double upperY = square.getY();
        double lowerY = square.getY() + square.getHeight();
        double closestY = closestCoord(upperY, lowerY, circle.getCenterY());

        double distanceX = (circle.getCenterX() - closestX);
        double distanceY = (circle.getCenterY() - closestY);
        double distance = Math.sqrt((distanceX * distanceX) +
                                    (distanceY * distanceY));

        return distance < circle.getRadius();
    }

    private static double closestCoord(double min, double max, double circleCenter) {
        return Math.max(min, Math.min(max, circleCenter));
    }
}