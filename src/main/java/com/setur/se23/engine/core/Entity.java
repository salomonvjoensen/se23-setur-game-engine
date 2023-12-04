package com.setur.se23.engine.core;

import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.Texture2D;

public abstract class Entity {
    protected Material material;
    protected double xPos;
    protected double yPos;
    protected double angle;
    protected double scaleX;
    protected double scaleY;
    
    public Entity(Material material, double xPos, double yPos, double angle, double scaleX, double scaleY) {
        this.material = material;
        this.xPos = xPos;
        this.yPos = yPos;
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

    public void uniformScale(double scalingFactor) {
        setScaleX(getScaleX() * scalingFactor);
        setScaleY(getScaleY() * scalingFactor);
    }

    public void scaleX(double scalingFactor) {
        setScaleX(getScaleX() * scalingFactor);
    }

    public void scaleY(double scalingFactor) {
        setScaleY(getScaleY() * scalingFactor);
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

    public int getTextureWidth() {
        return getTexture().width();
    }

    public int getTextureHeight() {
        return getTexture().height();
    }

    public int getWidth() {
        return (int)(getTextureWidth() * scaleX);
    }

    public int getHeight() {
        return (int)(getTextureHeight() * scaleY);
    }

    public void setWidth(int width) {
        setScaleX(width / getTextureWidth());
    }

    public void setHeight(int height) {
        setScaleX(height / getTextureHeight());
    }

    public void renderEntity() {
        BufferItem bufferItem = new BufferItem(material, xPos, yPos, angle, scaleX, scaleY);
        
        Renderer.getInstance().render(bufferItem);
    }

    public abstract void update(double deltaTime);
}
