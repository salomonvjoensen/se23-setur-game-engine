package com.setur.se23.engine.core;

import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.Texture2D;

public abstract class Entity {
    private Material material;
    private double xPos;
    private double yPos;
    private int width;
    private int height;
    private double angle = 0;
    
    public Entity(Material material, double xPos, double yPos, int width, int height) {
        this.material = material;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;

        BufferItem bufferItem = new BufferItem(material, xPos, yPos, angle);

        Renderer.getInstance().allocateTexture(bufferItem.material().texture());
    }

    public Material getMaterial() {
        return material;
    }

    public Texture2D getTexture() {
        return material.texture();
    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public void setX(double xPos) {
        this.xPos = xPos;
    }

    public void setY(double yPos) {
        this.yPos = yPos;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void renderEntity() {
        BufferItem bufferItem = new BufferItem(material, xPos, yPos, angle);
        
        Renderer.getInstance().render(bufferItem);
    }

    public abstract void update(double deltaTime);

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
