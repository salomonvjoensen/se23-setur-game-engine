package com.setur.se23.engine.core;

import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.Texture2D;

public abstract class Entity {
    private Material material;
    private double xPos;
    private double yPos;
    
    public Entity(Material material, double xPos, double yPos) {
        this.material = material;
        this.xPos = xPos;
        this.yPos = yPos;

        BufferItem bufferItem = new BufferItem(material, xPos, yPos);

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

    public void renderEntity() {
        BufferItem bufferItem = new BufferItem(material, xPos, yPos);
        
        Renderer.getInstance().render(bufferItem);
    }

    public abstract void update(double deltaTime);
}
