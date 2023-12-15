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

    /**
     * Constructor for physics instance.
     * 
     * @param horizontalSpeed starting horizontal speed.
     * @param verticalSpeed starting vertical speed.
     * @param horizontalAccel starting horizontal acceleration.
     * @param verticalAccel starting vertical acceleration.
     * @param maxHorizontalSpeed maximum horizontal speed.
     * @param maxVerticalSpeed maximum vertical speed.
     * @param minHorizontalSpeed minimum horizontal speed.
     * @param minVerticalSpeed minimum vertical speed.
     */
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

    /**
     * Method for updating velocity, speed and acceleration with deltaTime.
     * 
     * @param deltaTime
     */
    public void physicsUpdate(double deltaTime) {
        physicsX(deltaTime);
        physicsY(deltaTime);
    }

    /**
     * Updates already set velocity, speed and acceleration variables for x 
     * with deltaTime.
     * 
     * @param deltaTime
     */
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

    /**
     * Updates already set velocity, speed and acceleration variables for y 
     * with deltaTime.
     * 
     * @param deltaTime
     */
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

    public void setHorizontalAccel(double horizontalAccel) {
        this.horizontalAccel = horizontalAccel;
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

    public void setVerticalAccel(double verticalAccel) {
        this.verticalAccel = verticalAccel;
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
