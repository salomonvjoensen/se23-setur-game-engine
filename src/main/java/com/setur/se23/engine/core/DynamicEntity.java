package com.setur.se23.engine.core;

import com.setur.se23.engine.render.common.Material;

public abstract class DynamicEntity extends Entity {
    
    private double speed;
    private double xDir;
    private double yDir;

    public DynamicEntity(Material material, double xPos, double yPos, double speed) {
        super(material, xPos, yPos);
        this.speed = speed;
    }

    public double getXDir() {
        return xDir;
    }

    public double getYDir() {
        return yDir;
    }

    public double getSpeed() {
        return speed;
    }

    public void setXDir(double xDir) {
        this.xDir = xDir;
    }

    public void setYDir(double yDir) {
        this.yDir = yDir;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }    
}
