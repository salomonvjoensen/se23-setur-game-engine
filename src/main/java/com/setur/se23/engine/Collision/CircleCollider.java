package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class CircleCollider extends Collider {

    private int radius;
    private double scale;

    public CircleCollider(Entity attachedEntity, int radius) {
        super(new Material(
                    new Texture2D(Core.getResorcePath("gizmo/red_circle.png"), radius * 2, radius * 2), 
                    new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)),
              attachedEntity);
        this.radius = radius;
        this.scale = 1;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius * scale;
    }

    public double getX() {
        return attachedEntity.getX() + (attachedEntity.getWidth() / 2) - getRadius();
    }

    public double getY() {
        return attachedEntity.getY() + (attachedEntity.getHeight() / 2) - getRadius();
    }

    public double getCenterX() {
        return attachedEntity.getX() + (attachedEntity.getWidth() / 2);
    }

    public double getCenterY() {
        return attachedEntity.getY() + (attachedEntity.getHeight() / 2);
    }

    @Override
    public void uniformScale(double scalingFactor) {
        scale *= scalingFactor;
    }

    @Override
    public void RenderGizmo() {

        BufferItem bufferItem = new BufferItem(getMaterial(), getX(), getY(), 0, scale, scale);
        
        Renderer.getInstance().render(bufferItem);
    }
}
