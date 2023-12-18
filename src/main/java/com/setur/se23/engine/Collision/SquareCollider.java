package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.core.Resource;
import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Texture2D;

public class SquareCollider extends Collider {

    private int width;
    private int height;
    private double scaleX;
    private double scaleY;

    /**
     * Constructor for SquareCollider
     * 
     * @param attachedEntity the colliders entity
     * @param width the desired width
     * @param height the desired height
     */
    public SquareCollider(Entity attachedEntity, int width, int height) {
        super(new Texture2D(Resource.getResourcePath("gizmo/red_square.png"), width, height),
              attachedEntity);
        this.width = width;
        this.height = height;
        this.scaleX = 1;
        this.scaleY = 1;
    }

    /**
     * Method for setting width.
     * 
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Method for setting height.
     * 
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Method for getting width.
     * 
     * @return width
     */
    public double getWidth() {
        return width * scaleX;
    }

    /**
     * Method for getting height.
     * 
     * @return height
     */
    public double getHeight() {
        return height * scaleY;
    }

    /**
     * Method for getting x (upper left).
     * 
     * @return xPos
     */
    public double getX() {
        return attachedEntity.getX() + (attachedEntity.getWidth() / 2) - (getWidth() / 2);
    }

    /**
     * Method for getting y (upper left).
     * 
     * @return yPos
     */
    public double getY() {
        return attachedEntity.getY() + (attachedEntity.getHeight() / 2) - (getHeight() / 2);
    }

    /**
     * Method to scale collider
     */
    @Override
    public void uniformScale(double scalingFactor) {
        scaleX *= scalingFactor;
        scaleY *= scalingFactor;
    }

    /**
     * Method to render gizmo
     */
    @Override
    public void RenderGizmo() {

        BufferItem bufferItem = new BufferItem(getTexture(), getX(), getY(), 0, scaleX, scaleY);
        
        Renderer.getInstance().render(bufferItem);
    }
}
