package com.setur.se23.engine.Physics;

public class Physics {

    private double horizontalAccel;
    private double horizontalSpeed;
    private double minHorizontalSpeed;
    private double maxHorizontalSpeed;
    private double velocityX;
    
    private double verticalAccel;
    private double verticalSpeed;
    private double maxVerticalSpeed;
    private double minVerticalSpeed;
    private double velocityY;

    public Physics(double horizontalSpeed, double verticalSpeed,
                   double horizontalAccel, double verticalAccel,
                   double maxHorizontalSpeed, double maxVerticalSpeed,
                   double minHorizontalSpeed, double minVerticalSpeed) {

        this.horizontalSpeed = horizontalSpeed;
        this.horizontalAccel = horizontalAccel;
        this.verticalSpeed = verticalSpeed;
        this.verticalAccel = verticalAccel;
        this.maxHorizontalSpeed = maxHorizontalSpeed;
        this.minHorizontalSpeed = minHorizontalSpeed;
        this.maxVerticalSpeed = maxVerticalSpeed;
        this.minVerticalSpeed = minVerticalSpeed;
    }

    public void physicsUpdate(double deltaTime) {
        physicsX(deltaTime);
        physicsY(deltaTime);
    }

    private void physicsX(double deltaTime) {

        horizontalSpeed += horizontalSpeed * horizontalAccel * deltaTime;

        if (horizontalSpeed > maxHorizontalSpeed) {
            horizontalSpeed = maxHorizontalSpeed;
        }
        
        if (horizontalSpeed < minHorizontalSpeed) {
            horizontalSpeed = minHorizontalSpeed;
        }
        
        velocityX += horizontalSpeed * deltaTime;
    }

    private void physicsY(double deltaTime) {

        verticalSpeed += verticalSpeed * verticalAccel * deltaTime;

        if (verticalSpeed > maxVerticalSpeed) {
            verticalSpeed = maxVerticalSpeed;
        }
        
        if (verticalSpeed < minVerticalSpeed) {
            verticalSpeed = minVerticalSpeed;
        }

        velocityY += verticalSpeed * deltaTime;
    }

    public void setHorizontalSpeed(double horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVerticalSpeed(double verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
    
    public double getVelocityY() {
        return velocityY;
    }
}
