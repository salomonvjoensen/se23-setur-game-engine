package com.setur.se23.engine.Collision;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class SquareCollider extends Collider {

    private Material material;
    private int width;
    private int height;

    public SquareCollider(int width, int height) {
        this.material = new Material(
                            new Texture2D(Core.getResorcePath("gizmo/red_square.png"), width, height), 
                            new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f));
        this.width = width;
        this.height = height;

        double gizmoX = (width / 2) - (getColliderWidth() / 2);
        double gizmoY = (height / 2) - (getColliderHeight() / 2);
        
        BufferItem bufferItem = new BufferItem(material, gizmoX, gizmoY, 0, 1, 1);

        Renderer.getInstance().allocateTexture(bufferItem.material().texture());
    }

    public boolean isInArea(Entity a, Entity b) {


        return false;
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

    @Override
    public void RenderGizmo(double entityX, double entityY, int entityWidth, int entityHeight) {

        double gizmoX = entityX + (entityWidth / 2) - (getColliderWidth() / 2);
        double gizmoY = entityY + (entityHeight / 2) - (getColliderHeight() / 2);
        
        BufferItem bufferItem = new BufferItem(material, gizmoX, gizmoY, 0, 1, 1);
        
        Renderer.getInstance().render(bufferItem);
    }
}
