package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Texture2D;

public abstract class Collider {

    protected Texture2D texture;
    protected Entity attachedEntity;

    /**
     * Constructor for Collider
     * 
     * @param texture Collider texture (intended to be gizmo texture)
     * @param attachedEntity the colliders entity
     */
    public Collider(Texture2D texture, Entity attachedEntity) {
        this.texture = texture;
        this.attachedEntity = attachedEntity;

        BufferItem bufferItem = new BufferItem(texture, 0, 0, 0, 1, 1);

        Renderer.getInstance().allocateTexture(bufferItem.texture());
    }

    /**
     * Method for getting texture (i.e. gizmo)
     * 
     * @return texture
     */
    public Texture2D getTexture() {
        return texture;
    }

    public abstract double getX();

    public abstract double getY();

    public abstract void uniformScale(double scalingFactor);

    public abstract void RenderGizmo();
}
