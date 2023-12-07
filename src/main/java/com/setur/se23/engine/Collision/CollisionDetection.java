package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Entity;

public class CollisionDetection {
    /**
     * @param a an entity
     * @param b a second entity
     * @return return true if a collides with b using collision detection
     */
    public static boolean checkForCollision(Entity entity1, Entity entity2) {

        Collider collider1 = ((Collidable) entity1).getCollider();
        Collider collider2 = ((Collidable) entity2).getCollider();

        if (collider1 instanceof SquareCollider && collider2 instanceof SquareCollider) {
            return squaresCollide((SquareCollider) collider1, (SquareCollider) collider2);
        }

        if (collider1 instanceof CircleCollider && collider2 instanceof CircleCollider) {
            return circlesCollide((CircleCollider) collider1, (CircleCollider) collider2);
        }

        if (collider1 instanceof SquareCollider && collider2 instanceof CircleCollider) {
            return squareCircleCollide((SquareCollider) collider1, (CircleCollider) collider2);
        }

        if (collider1 instanceof CircleCollider && collider2 instanceof SquareCollider) {
            return squareCircleCollide((SquareCollider) collider2, (CircleCollider) collider1);
        }

        return false;
    }

    private static boolean squaresCollide(SquareCollider square1, SquareCollider square2) {
        return square1.getX() + square1.getWidth()  > square2.getX() &&
               square2.getX() + square2.getWidth()  > square1.getX() &&
               square1.getY() + square1.getHeight() > square2.getY() &&
               square2.getY() + square2.getHeight() > square1.getY();
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