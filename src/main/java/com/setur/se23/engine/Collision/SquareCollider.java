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

    public SquareCollider(Entity attachedEntity, int width, int height) {
        super(new Material(
                    new Texture2D(Core.getResorcePath("gizmo/red_square.png"), width, height), 
                    new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)),
              attachedEntity);
        this.width = width;
        this.height = height;
    }

    public void setColliderWidth(int width) {
        this.width = width;
    }

    public void setColliderHeight(int height) {
        this.height = height;
    }

    public double getColliderWidth() {
        return width;
    }

    public double getColliderHeight() {
        return height;
    }

    public double getX() {
        return attachedEntity.getX() + (attachedEntity.getWidth() / 2) - (getColliderWidth() / 2);
    }

    public double getY() {
        return attachedEntity.getY() + (attachedEntity.getHeight() / 2) - (getColliderHeight() / 2);
    }

    @Override
    public void RenderGizmo() {

        BufferItem bufferItem = new BufferItem(getMaterial(), getX(), getY(), 0, 1, 1);
        
        Renderer.getInstance().render(bufferItem);
    }
}
