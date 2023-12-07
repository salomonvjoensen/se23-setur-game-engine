package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class SquareCollider extends Collider {

    private int width;
    private int height;
    private double scaleX;
    private double scaleY;

    public SquareCollider(Entity attachedEntity, int width, int height) {
        super(new Material(
                    new Texture2D(null, width, height), 
                    new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)),
              attachedEntity);
        this.width = width;
        this.height = height;
        this.scaleX = 1;
        this.scaleY = 1;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWidth() {
        return width * scaleX;
    }

    public double getHeight() {
        return height * scaleY;
    }

    public double getX() {
        return attachedEntity.getX() + (attachedEntity.getWidth() / 2) - (getWidth() / 2);
    }

    public double getY() {
        return attachedEntity.getY() + (attachedEntity.getHeight() / 2) - (getHeight() / 2);
    }

    @Override
    public void uniformScale(double scalingFactor) {
        scaleX *= scalingFactor;
        scaleY *= scalingFactor;
    }

    @Override
    public void RenderGizmo() {

        BufferItem bufferItem = new BufferItem(getMaterial(), getX(), getY(), 0, scaleX, scaleY);
        
        Renderer.getInstance().render(bufferItem);
    }
}
