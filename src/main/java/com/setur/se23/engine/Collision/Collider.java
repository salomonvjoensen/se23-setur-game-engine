package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Texture2D;

public abstract class Collider {

    protected Texture2D texture;
    protected Entity attachedEntity;

    public Collider(Texture2D texture, Entity attachedEntity) {
        this.texture = texture;
        this.attachedEntity = attachedEntity;

        BufferItem bufferItem = new BufferItem(texture, 0, 0, 0, 1, 1);

        Renderer.getInstance().allocateTexture(bufferItem.texture());
    }

    public Texture2D getMaterial() {
        return texture;
    }

    public abstract double getX();

    public abstract double getY();

    public abstract void uniformScale(double scalingFactor);

    public abstract void RenderGizmo();
}
