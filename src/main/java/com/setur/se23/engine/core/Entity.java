package com.setur.se23.engine.core;

import java.util.ArrayList;

import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Texture2D;

public abstract class Entity {
    protected ArrayList<Texture2D> textureList = new ArrayList<>();
    protected int currentTextureIndex;
    protected double xPos;
    protected double yPos;
    protected double angle;
    protected double scaleX;
    protected double scaleY;
    
    public Entity(Texture2D texture, double xPos, double yPos, double angle, double scaleX, double scaleY) {
        this.textureList.add(texture);
        this.currentTextureIndex = 0;
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
        this.scaleX = scaleX;
        this.scaleY = scaleY;

        BufferItem bufferItem = new BufferItem(texture, xPos, yPos, angle, scaleX, scaleY);

        Renderer.getInstance().allocateTexture(bufferItem.texture());
    }

    public void addTexture(Texture2D texture) {
        this.textureList.add(texture);

        BufferItem bufferItem = new BufferItem(texture, xPos, yPos, angle, scaleX, scaleY);

        Renderer.getInstance().allocateTexture(bufferItem.texture());
    }

    public void changeMaterial(int index) {
        currentTextureIndex = index;
    }

    public Texture2D getCurrentTexture() {
        return textureList.get(currentTextureIndex);
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
        return getCurrentTexture().width();
    }

    public int getTextureHeight() {
        return getCurrentTexture().height();
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
        BufferItem bufferItem = new BufferItem(textureList.get(currentTextureIndex), xPos, yPos, angle, scaleX, scaleY);
        
        Renderer.getInstance().render(bufferItem);
    }
}
