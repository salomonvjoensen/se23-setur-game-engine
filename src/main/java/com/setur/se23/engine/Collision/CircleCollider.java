package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.core.Resource;
import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Texture2D;

public class CircleCollider extends Collider {

    private int radius;
    private double scale;

    /**
     * Constructor for CircleCollider
     * 
     * @param attachedEntity the colliders entity
     * @param radius the desired radius
     */
    public CircleCollider(Entity attachedEntity, int radius) {
        super(new Texture2D(Resource.getResourcePath("gizmo/red_circle.png"), radius * 2, radius * 2),
              attachedEntity);
        this.radius = radius;
        this.scale = 1;
    }

    /**
     * Method for setting radius
     * 
     * @param radius desired radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Method for getting radius
     * 
     * @return radius
     */
    public double getRadius() {
        return radius * scale;
    }

    /**
     * Method for getting x (upper left).
     * 
     * @return xPos
     */
    public double getX() {
        return attachedEntity.getX() + (attachedEntity.getWidth() / 2) - getRadius();
    }

    /**
     * Method for getting y (upper left).
     * 
     * @return yPos
     */
    public double getY() {
        return attachedEntity.getY() + (attachedEntity.getHeight() / 2) - getRadius();
    }

    /**
     * Method for getting center x.
     * 
     * @return center x
     */
    public double getCenterX() {
        return attachedEntity.getX() + (attachedEntity.getWidth() / 2);
    }

    /**
     * Method for getting center y.
     * 
     * @return center y
     */
    public double getCenterY() {
        return attachedEntity.getY() + (attachedEntity.getHeight() / 2);
    }

    /**
     * Method to scale collider
     */
    @Override
    public void uniformScale(double scalingFactor) {
        scale *= scalingFactor;
    }

    /**
     * Method to render gizmo
     */
    @Override
    public void RenderGizmo() {

        BufferItem bufferItem = new BufferItem(getTexture(), getX(), getY(), 0, scale, scale);
        
        Renderer.getInstance().render(bufferItem);
    }
}
