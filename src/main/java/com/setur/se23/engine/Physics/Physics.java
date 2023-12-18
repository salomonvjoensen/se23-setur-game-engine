package com.setur.se23.engine.Physics;

public class Physics {

    private double horizontalAccelGrowth;
    private double horizontalAccel;
    private double minHorizontalAccel;
    private double maxHorizontalAccel;
    private double velocityX;
    
    private double verticalAccelGrowth;
    private double verticalAccel;
    private double maxVerticalAccel;
    private double minVerticalAccel;
    private double velocityY;

    /**
     * Constructor for physics instance. 
     * Accel is the rate of change of velocity.
     * AccelGrowth is the rate of change of acceleration (1 is +100% per second).
     * min and max are the upper and lower bounds of accel growth.
     * 
     * @param horizontalAccel starting horizontal acceleration.
     * @param verticalAccel starting vertical acceleration.
     * @param horizontalAccelGrowth starting horizontal acceleration growth.
     * @param verticalAccelGrowth starting vertical acceleration growth.
     * @param maxHorizontalAccel maximum horizontal acceleration.
     * @param maxVerticalAccel maximum vertical acceleration.
     * @param minHorizontalAccel minimum horizontal acceleration.
     * @param minVerticalAccel minimum vertical acceleration.
     */
    public Physics(double horizontalAccel, double verticalAccel,
                   double horizontalAccelGrowth, double verticalAccelGrowth,
                   double maxHorizontalAccel, double maxVerticalAccel,
                   double minHorizontalAccel, double minVerticalAccel) {

        this.horizontalAccel = horizontalAccel;
        this.horizontalAccelGrowth = horizontalAccelGrowth;
        this.verticalAccel = verticalAccel;
        this.verticalAccelGrowth = verticalAccelGrowth;
        this.maxHorizontalAccel = maxHorizontalAccel;
        this.minHorizontalAccel = minHorizontalAccel;
        this.maxVerticalAccel = maxVerticalAccel;
        this.minVerticalAccel = minVerticalAccel;
    }

    /**
     * Method for updating velocity and acceleration with deltaTime.
     * 
     * @param deltaTime
     */
    public void physicsUpdate(double deltaTime) {
        physicsX(deltaTime);
        physicsY(deltaTime);
    }

    /**
     * Updates already set velocity and acceleration variables for x 
     * with deltaTime.
     * 
     * @param deltaTime
     */
    private void physicsX(double deltaTime) {

        horizontalAccel += horizontalAccel * horizontalAccelGrowth * deltaTime;

        if (horizontalAccel > maxHorizontalAccel) {
            horizontalAccel = maxHorizontalAccel;
        }
        
        if (horizontalAccel < minHorizontalAccel) {
            horizontalAccel = minHorizontalAccel;
        }
        
        velocityX += horizontalAccel * deltaTime;
    }

    /**
     * Updates already set velocity and acceleration variables for y 
     * with deltaTime.
     * 
     * @param deltaTime
     */
    private void physicsY(double deltaTime) {

        verticalAccel += verticalAccel * verticalAccelGrowth * deltaTime;

        if (verticalAccel > maxVerticalAccel) {
            verticalAccel = maxVerticalAccel;
        }
        
        if (verticalAccel < minVerticalAccel) {
            verticalAccel = minVerticalAccel;
        }

        velocityY += verticalAccel * deltaTime;
    }

    /**
     * Method for setting horizontal acceleration growth.
     * 
     * @param horizontalAccelGrowth
     */
    public void setHorizontalAccelGrowth(double horizontalAccelGrowth) {
        this.horizontalAccelGrowth = horizontalAccelGrowth;
    }

    /**
     * Method for setting horizontal acceleration.
     * 
     * @param horizontalAccel
     */
    public void setHorizontalAccel(double horizontalAccel) {
        this.horizontalAccel = horizontalAccel;
    }

    /**
     * Method for setting velocity x.
     * 
     * @param velocityX
     */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    /**
     * Method for getting velocity x.
     * 
     * @return velocityX
     */
    public double getVelocityX() {
        return velocityX;
    }

    /**
     * Method for setting vertical acceleration growth.
     * 
     * @param verticalAccelGrowth
     */
    public void setVerticalAccelGrowth(double verticalAccelGrowth) {
        this.verticalAccelGrowth = verticalAccelGrowth;
    }

    /**
     * Method for setting vertical acceleration.
     * 
     * @param verticalAccel
     */
    public void setVerticalAccel(double verticalAccel) {
        this.verticalAccel = verticalAccel;
    }

    /**
     * Method for setting velocity y.
     * 
     * @param velocityY
     */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
    
    /**
     * Method for getting velocity y.
     * 
     * @return velocityY
     */
    public double getVelocityY() {
        return velocityY;
    }
}
