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
    private double angle;
    private double scaleX;
    private double scaleY;
    
    public Entity(Material material, double xPos, double yPos, int width, int height, double angle, double scaleX, double scaleY) {
        this.material = material;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.scaleX = scaleX;
        this.scaleY = scaleY;

        BufferItem bufferItem = new BufferItem(material, xPos, yPos, angle, scaleX, scaleY);

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

    public void UniformScale(double scale) {
        setScaleX(getScaleX() * scale);
        setScaleY(getScaleY() * scale);
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    public double getScaleX() {
        return scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public int getWidth() {
        return (int)(width * scaleX);
    }

    public int getHeight() {
        return (int)(height * scaleY);
    }

    public void renderEntity() {
        BufferItem bufferItem = new BufferItem(material, xPos, yPos, angle, scaleX, scaleY);
        
        Renderer.getInstance().render(bufferItem);
    }

    public abstract void update(double deltaTime);
}
